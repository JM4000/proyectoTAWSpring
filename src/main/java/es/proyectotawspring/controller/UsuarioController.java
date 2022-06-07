package es.proyectotawspring.controller;


import es.proyectotawspring.Util.FiltroPaginaPrincipal;
import es.proyectotawspring.dto.CategoriaDTO;
import es.proyectotawspring.dto.ProductoDTO;
import es.proyectotawspring.dto.SubastaDTO;
import es.proyectotawspring.dto.UsuarioDTO;
import es.proyectotawspring.service.CategoriaService;
import es.proyectotawspring.service.ProductoService;
import es.proyectotawspring.service.SubastaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("usuario")
public class UsuarioController extends ProyectoTawController {
    private ProductoService productoService;
    private CategoriaService categoriaService;
    private SubastaService subastaService;

    public CategoriaService getCategoriaService() {
        return categoriaService;
    }

    @Autowired
    public void setCategoriaService(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    public SubastaService getSubastaService() {
        return subastaService;
    }
    @Autowired
    public void setSubastaService(SubastaService subastaService) {
        this.subastaService = subastaService;
    }

    public ProductoService getProductoService() {
        return productoService;
    }
    @Autowired
    public void setProductoService(ProductoService productoService) {
        this.productoService = productoService;
    }



    @GetMapping("/{id}/misProductos")
    public String doListarMisProductos(@PathVariable("id") Integer id, Model model, HttpSession session){
        if(super.redirigirUsuario("Estandar", session)){
            return"redirect:/";
        }else{

            List<ProductoDTO> productos = this.productoService.findAll();
            model.addAttribute("productos",productos);
            model.addAttribute("errorCategorias","");
            return "listaProductosEnVenta";
        }
    }

    @GetMapping("{id}/paginaPrincipal")
    public String doIniciarPaginaPrincipal(@PathVariable("id") Integer id, Model model, HttpSession session){
        Boolean fav=false,comp=false;
        String listaTipo = "PRODUCTOS EN SUBASTA ";
        List <CategoriaDTO> categorias = this.categoriaService.findAll();



        List <SubastaDTO> subastas = this.subastaService.SubastaActiva("","");
        FiltroPaginaPrincipal filtroPaginaPrincipal = new FiltroPaginaPrincipal();

        filtroPaginaPrincipal.setCategoriaNombre(categorias.get(0).getNombre());

        model.addAttribute("categorias",categorias);
        model.addAttribute("subastas",subastas);
        model.addAttribute("filtroPaginaPrincipal",filtroPaginaPrincipal );

        model.addAttribute("listaTipo",listaTipo);
        model.addAttribute("fav",fav);
        model.addAttribute("comp",comp);


        return "paginaPrincipal";

    }
    @GetMapping("{id}/filtroPaginaPrincipal")
    public String doFiltrarPaginaPrincipal(@PathVariable("id") int id, @ModelAttribute("filtroPaginaPrincipal") FiltroPaginaPrincipal filtroPaginaPrincipal ,Model model, HttpSession session){

        //DATOS
        //-----------------------------------------------------------------
        Boolean fav=false,comp=false;
        String listaTipo = "PRODUCTOS EN SUBASTA ";
        List <CategoriaDTO> categorias = this.categoriaService.findAll();

        String filtro = filtroPaginaPrincipal.getFiltro();

        String categoria = filtroPaginaPrincipal.getCategoriaNombre();

        if (categoria == null)categoria="";

        String titulo = filtroPaginaPrincipal.getBusqueda();
        if (titulo == null)titulo = "";




        List <SubastaDTO> subastas = this.subastaService.SubastaActiva(titulo,categoria);


        //FILTROS
        //-----------------------------------------------------------------

        if (filtro.equals("favoritos")){
            subastas = this.subastaService.SubastaProductosFavoritos(id,titulo,categoria);
            listaTipo = "PRODUCTOS DE SUBASTAS EN FAVORITO " ;
            fav=true;
        } else if (filtro.equals("comprados")){
            subastas = this.subastaService.SubastaProductosComprados(new Integer (id),titulo,categoria);
            listaTipo = "PRODUCTOS YA COMPRADOS " + categoria;
            comp=true;
        }
        if (!categoria.equals("") ) listaTipo+= " DE CATEGORIA '"+ categoria.toUpperCase() +"'";
        if(!titulo.equals(""))listaTipo+= " QUE CONTENGAN  '"  + titulo.toUpperCase() +"' ";

        //-----------------------------------------------------------------
        model.addAttribute("categorias",categorias);

        model.addAttribute("listaTipo",listaTipo);
        model.addAttribute("fav",fav);
        model.addAttribute("comp",comp);

        model.addAttribute("subastas",subastas);


        return "paginaPrincipal";
    }



}
