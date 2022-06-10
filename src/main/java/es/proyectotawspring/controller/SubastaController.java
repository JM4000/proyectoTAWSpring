package es.proyectotawspring.controller;

import es.proyectotawspring.dto.CategoriaDTO;
import es.proyectotawspring.dto.ProductoDTO;
import es.proyectotawspring.dto.SubastaDTO;
import es.proyectotawspring.dto.UsuarioDTO;
import es.proyectotawspring.entity.ProductoEntity;
import es.proyectotawspring.service.CategoriaService;
import es.proyectotawspring.service.ProductoService;
import es.proyectotawspring.service.SubastaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
           // supongo que está en cascada y si se borra el producto, se borra la subasta
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
            this.subastaService.editarCompradorSubasta(p.getIdProducto(),s.getMayorPostor().getIdUsuario());

            UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
            return "redirect:/usuario/"+usuario.getIdUsuario()+"/misProductos";
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
    @PostMapping("/guardarSubasta")
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

               // Producto producto = this.pFacade.crearProductoSubasta(title, desc, foto, precio, categoriasFinales);
               // this.sFacade.crearSubasta(user.getIdUsuario(), precio, producto.getIdProducto(), fecha);


                ProductoEntity p = this.productoService.crearProductoSubasta(producto.getTitulo(),producto.getDescripcion(),producto.getFoto(),producto.getPrecioSalida(),producto.getCategoriaList());
                UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
                this.subastaService.crearSubasta(fecha,p.getIdProducto(),p.getPrecioSalida(),usuario.getIdUsuario());



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
            } else { // caso en el que los datos están correcto
                //OBTENGO EL PRODUCTO COMO ERA ANTES PARA OBTENER LAS CATEGORIAS DE ANTES CON EL FIND
                ProductoDTO productoDTO= this.productoService.find(producto.getIdProducto());
// als categorias antiguas están en productoDTO.getCategoriaList()

                List<CategoriaDTO> categoriasFinales = new ArrayList<CategoriaDTO>();
                for(String nombre : producto.getCategoriaList()){
                    categoriasFinales.add(this.categoriaService.findByNombre(nombre).toDTO());
                }



                // si la categoria antes no estaba y ahroa sí, lo introducimos ene la lsita, sino , lo borramos
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
