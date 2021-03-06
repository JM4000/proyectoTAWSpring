package es.proyectotawspring.controller;

import es.proyectotawspring.Util.ObjetoPuja;
import es.proyectotawspring.dto.CategoriaDTO;
import es.proyectotawspring.dto.ProductoDTO;
import es.proyectotawspring.dto.SubastaDTO;
import es.proyectotawspring.dto.UsuarioDTO;
import es.proyectotawspring.entity.CategoriaEntity;
import es.proyectotawspring.entity.ProductoEntity;
import es.proyectotawspring.service.CategoriaService;
import es.proyectotawspring.service.ProductoService;
import es.proyectotawspring.service.SubastaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("subasta")
public class SubastaController extends ProyectoTawController{
    private ProductoService productoService;
    private SubastaService subastaService;
    private CategoriaService categoriaService;

    public CategoriaService getCategoriaService() {
        return categoriaService;
    }

    @Autowired
    public void setCategoriaService(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    public ProductoService getProductoService() {
        return productoService;
    }
@Autowired
    public void setProductoService(ProductoService productoService) {
        this.productoService = productoService;
    }

    public SubastaService getSubastaService() {
        return subastaService;
    }
@Autowired
    public void setSubastaService(SubastaService subastaService) {
        this.subastaService = subastaService;
    }

    @GetMapping("/{idProducto}/EditarSubasta")
    public String doEditarSubasta(@PathVariable("idProducto") Integer idProducto, HttpSession session, Model model){
        if (super.redirigirUsuario("Estandar", session)) {
            return "redirect:/";
        } else {
            ProductoDTO producto= this.productoService.find(idProducto);
           SubastaDTO subasta = this.subastaService.findSubastaActiva(idProducto);
            model.addAttribute("errorCategorias","");
            model.addAttribute("categorias", this.categoriaService.findAll());
            model.addAttribute("producto",producto);
            model.addAttribute("subasta",subasta);
            return "editarSubasta";
        }
    }

    @GetMapping("/{idProducto}/EliminarSubasta")
    public String doBorrarSubasta(@PathVariable("idProducto") Integer idProducto, HttpSession session){
        if (super.redirigirUsuario("Estandar", session)) {
            return "redirect:/";
        } else {
           // supongo que est?? en cascada y si se borra el producto, se borra la subasta
            this.productoService.borrar(idProducto);

            UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
            return "redirect:/usuario/"+usuario.getIdUsuario()+"/misProductos";
        }
    }


    @PostMapping("/cerrar")
    public String doCerrarSubasta(@RequestParam("idSubasta") Integer idSubasta, HttpSession session){
        if (super.redirigirUsuario("Estandar", session)) {
            return "redirect:/";
        } else {
            SubastaDTO s = this.subastaService.findBySubastaId(idSubasta);
            this.subastaService.editarCierreSubasta(s,new Date());


            ProductoDTO p = s.getProducto();
            if(s.getMayorPostor()!=null){
                this.subastaService.editarCompradorSubasta(p.getIdProducto(),s.getMayorPostor().getIdUsuario());

            }

            UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
            return "redirect:/usuario/"+usuario.getIdUsuario()+"/misProductos";
        }
    }


    @GetMapping("/pujar/{idSubasta}")
    public String doPujar(@PathVariable("idSubasta") Integer idSubasta,Model model,HttpSession session){
        if (super.redirigirUsuario("Estandar", session)) {
            return "redirect:/";
        } else {
            SubastaDTO subasta= this.subastaService.findBySubastaId(idSubasta);
            ObjetoPuja objetoPuja = new ObjetoPuja();
            objetoPuja.setIdSubasta(idSubasta);


            UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
            objetoPuja.setIdMayorPostor(usuario.getIdUsuario());


            ProductoDTO productoDTO= this.productoService.find(subasta.getProducto().getIdProducto());
            model.addAttribute("objetoPuja",objetoPuja);
            model.addAttribute("error","");
            model.addAttribute("subasta", subasta);
            model.addAttribute("producto",productoDTO);
            return "pujas";
        }
    }



    @PostMapping("/guardarPuja")
    public String doGuardarPuja(@ModelAttribute("objetoPuja")ObjetoPuja objetoPuja,Model model, HttpSession session) throws MissingServletRequestParameterException{

        if (super.redirigirUsuario("Estandar", session)) {
            return "redirect:/";
        } else {
            SubastaDTO subasta = this.subastaService.findBySubastaId(objetoPuja.getIdSubasta());
            String strError = "";


            if(objetoPuja.getCantidad().isEmpty() || objetoPuja.getCantidad()==null){
                strError = "** Indique cantidad a pujar";
            } else  if(Double.parseDouble(objetoPuja.getCantidad()) > subasta.getPredioActual()){

                this.subastaService.guardarPuja(objetoPuja.getIdSubasta(), Double.parseDouble(objetoPuja.getCantidad()), objetoPuja.getIdMayorPostor());

                subasta = subastaService.findBySubastaId(objetoPuja.getIdSubasta()); // esto es necesario porque se han cambiado los parametros
                strError= "Enhorabuena, has pujado " + objetoPuja.getCantidad() + " euros";
            }else{
                strError = "** No puedes pujar un precio menor a la puja mayor";
            }


            model.addAttribute("objetoPuja",objetoPuja);
            model.addAttribute("producto",subasta.getProducto());
            model.addAttribute("subasta",subasta);
            model.addAttribute("error",strError);
            return "pujas";
        }
    }


    @GetMapping("/crearSubasta")
    public String doCrearSubasta(HttpSession session, Model model){
        if (super.redirigirUsuario("Estandar", session)) {
            return "redirect:/";
        } else {
            model.addAttribute("producto", new ProductoDTO());
            model.addAttribute("errorCategorias","");
            model.addAttribute("categorias", this.categoriaService.findAll());
            return "crearSubasta";
        }
    }
    @PostMapping("/guardarSubasta")  // no se guardan bien las categorias
    public String doGuardarNuevaSubasta(@ModelAttribute("producto")ProductoDTO producto, @RequestParam("fecha") String strFecha,HttpSession session,Model model) throws ParseException{
        if (super.redirigirUsuario("Estandar", session)) {
            return "redirect:/";
        } else {

            if (producto.getCategoriaList() == null || producto.getCategoriaList().size() == 0) { // caso en el que las categorias seleccionadas son nulas
                String strError = "**Error, debes seleccionar al menos una categoria";
                List<CategoriaDTO> categoriasTotales = this.categoriaService.findAll();
                model.addAttribute("errorCategorias", strError);
                model.addAttribute("producto", producto);
                model.addAttribute("fecha",strFecha);
                model.addAttribute("categorias", categoriasTotales);
                return "crearSubasta";
            } else {

                //temrinar esta parte
                SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
                Date fecha = format.parse(strFecha);
                ProductoEntity p = this.productoService.crearProductoSubasta(producto.getTitulo(),producto.getDescripcion(),producto.getFoto(),producto.getPrecioSalida(),producto.getCategoriaList());
                UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
                this.subastaService.crearSubasta(fecha,p.getIdProducto(),p.getPrecioSalida(),usuario.getIdUsuario());

                for (CategoriaEntity c : p.getCategoriaList()) {
                    this.categoriaService.editarRelacionCategoriaProducto(p.getIdProducto(), c.getIdCategoria());
                }

                    return "redirect:/usuario/" + usuario.getIdUsuario() + "/misProductos";
            }
        }

    }

//terminar Guardar Edicion -> solo falla al borrar un una categoria, que no se borra bien
    @PostMapping("/guardarEdicion")
    public String doGuardarEdicion(@ModelAttribute("producto")ProductoDTO producto,@RequestParam("fecha") String strFecha,@RequestParam("idSubasta") Integer idSubasta, Model model, HttpSession session) throws ParseException {
        if (super.redirigirUsuario("Estandar", session)) {
            return "redirect:/";
        } else {

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formato.parse(strFecha);
            List<CategoriaDTO> categoriasTotales = this.categoriaService.findAll();

            if (producto.getCategoriaList() == null || producto.getCategoriaList().size() == 0) { // caso en el que las categorias seleccionadas son nulas
                String strError = "**Error, debes seleccionar al menos una categoria";
                SubastaDTO subasta = this.subastaService.findBySubastaId(idSubasta);
                model.addAttribute("errorCategorias", strError);
                model.addAttribute("producto", producto);
                model.addAttribute("subasta", subasta);
                model.addAttribute("categorias", categoriasTotales);
                return "editarSubasta";
            } else { // caso en el que los datos est??n correcto
                //OBTENGO EL PRODUCTO COMO ERA ANTES PARA OBTENER LAS CATEGORIAS DE ANTES CON EL FIND
                ProductoDTO productoDTO= this.productoService.find(producto.getIdProducto());
// als categorias antiguas est??n en productoDTO.getCategoriaList()

                List<CategoriaDTO> categoriasFinales = new ArrayList<CategoriaDTO>();
                for(String nombre : producto.getCategoriaList()){
                    categoriasFinales.add(this.categoriaService.findByNombre(nombre).toDTO());
                }



                // si la categoria antes no estaba y ahroa s??, lo introducimos ene la lsita, sino , lo borramos
                for (CategoriaDTO c : categoriasTotales) {
                    if (productoDTO.getCategoriaList().contains(c.getNombre()) && !producto.getCategoriaList().contains(c.getNombre())) {
                        this.categoriaService.borrarRelacionCategoriaProducto(producto.getIdProducto(), c.getIdCategoria());
                    }
                    if(!productoDTO.getCategoriaList().contains(c.getNombre()) && producto.getCategoriaList().contains(c.getNombre())){
                        this.categoriaService.editarRelacionCategoriaProducto(producto.getIdProducto(), c.getIdCategoria());
                    }




                    SubastaDTO subasta = this.subastaService.findBySubastaId(idSubasta);
                this.subastaService.editarCierreSubasta(subasta, fecha);
                this.productoService.editar(producto.getIdProducto(), producto.getTitulo(), producto.getDescripcion(), producto.getFoto(), categoriasFinales);
                }

                UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
                return "redirect:/usuario/" + usuario.getIdUsuario() + "/misProductos";
            }
        }


    }}
