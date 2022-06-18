package es.proyectotawspring.controller;


import es.proyectotawspring.Util.Busqueda;
import es.proyectotawspring.Util.FiltroPaginaPrincipal;
import es.proyectotawspring.dto.*;
import es.proyectotawspring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("usuario")
public class UsuarioController extends ProyectoTawController {
    private ProductoService productoService;
    private CategoriaService categoriaService;
    private SubastaService subastaService;
    private NotificacionService notificacionService;

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

    public NotificacionService getNotificacionService() {
        return notificacionService;
    }

    @Autowired
    public void setNotificacionService(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @GetMapping("/{id}/misProductos")
    public String doListarMisProductos(@PathVariable("id") Integer id, Model model, HttpSession session){
       /* if(super.redirigirUsuario("Estandar", session)){
            return"redirect:/";
        }else{ */
            List<ProductoDTO> productos = this.productoService.findAll();
            model.addAttribute("busqueda", new Busqueda());
            model.addAttribute("productos",productos);
            model.addAttribute("errorCategorias","");
            return "listaProductosEnVenta";
      /*  }*/
    }

    @GetMapping("/{id}/paginaPrincipal")
    public String doIniciarPaginaPrincipal(@PathVariable("id") Integer id, Model model, HttpSession session){
        if (super.redirigirUsuario("Estandar", session)) {
            return "redirect:/";
        }else {
            Boolean fav = false, comp = false;
            String listaTipo = "PRODUCTOS EN SUBASTA ";
            List<CategoriaDTO> categorias = this.categoriaService.findAll();


            List<SubastaDTO> subastas = this.subastaService.SubastaActiva("", "");

            FiltroPaginaPrincipal filtroPaginaPrincipal = new FiltroPaginaPrincipal();
            filtroPaginaPrincipal.setIdUsuario(id);


            model.addAttribute("categorias", categorias);
            model.addAttribute("subastas", subastas);
            model.addAttribute("filtroPaginaPrincipal", filtroPaginaPrincipal);

            model.addAttribute("listaTipo", listaTipo);
            model.addAttribute("fav", fav);
            model.addAttribute("comp", comp);


            return "paginaPrincipal";
        }
    }


    @PostMapping("/filtroMisProductos")
    public String doFiltradoMisProductos(Model model, HttpSession session, @ModelAttribute("busqueda")Busqueda busqueda){
    //    if (super.redirigirUsuario("Estandar", session)) {
      //      return "redirect:/";
       // } else {

            List<ProductoDTO> productos;
            String like = (String) busqueda.getBusqueda();
            Integer filtro = busqueda.getFiltro();

            productos = this.productoService.findFiltered(filtro, like);

            model.addAttribute("productos", productos);
            model.addAttribute("errorCategorias","");


            return "listaProductosEnVenta";
        //}
    }

    @PostMapping("/filtroPaginaPrincipal")
    public String doFiltrarPaginaPrincipal(  @ModelAttribute("filtroPaginaPrincipal") FiltroPaginaPrincipal filtroPaginaPrincipal ,Model model, HttpSession session){
        if (super.redirigirUsuario("Estandar", session)) {
            return "redirect:/";
        }else {
            //DATOS
            //-----------------------------------------------------------------
            Boolean fav = false, comp = false;
            String listaTipo = "PRODUCTOS EN SUBASTA ";

            List<CategoriaDTO> categorias = this.categoriaService.findAll();


            String filtro = filtroPaginaPrincipal.getFiltro();
            String categoria = filtroPaginaPrincipal.getCategoriaNombre();
            if (categoria == null) categoria = "";
            String titulo = filtroPaginaPrincipal.getBusqueda();
            if (titulo == null) titulo = "";

            List<SubastaDTO> subastas = this.subastaService.SubastaActiva(titulo, categoria);
            int id = filtroPaginaPrincipal.getIdUsuario();


            //FILTROS
            //-----------------------------------------------------------------

            if (filtro.equals("favoritos")) {
                subastas = this.subastaService.SubastaProductosFavoritos(id, titulo, categoria);
                listaTipo = "PRODUCTOS DE SUBASTAS EN FAVORITO ";
                fav = true;
            } else if (filtro.equals("comprados")) {
                subastas = this.subastaService.SubastaProductosComprados(id, titulo, categoria);
                listaTipo = "PRODUCTOS YA COMPRADOS " + categoria;
                comp = true;
            }
            if (!categoria.equals("")) listaTipo += " DE CATEGORIA '" + categoria.toUpperCase() + "'";
            if (!titulo.equals("")) listaTipo += " QUE CONTENGAN  '" + titulo.toUpperCase() + "' ";

            //-----------------------------------------------------------------
            model.addAttribute("categorias", categorias);
            model.addAttribute("filtroPaginaPrincipal", filtroPaginaPrincipal);
            model.addAttribute("listaTipo", listaTipo);
            model.addAttribute("fav", fav);
            model.addAttribute("comp", comp);
            model.addAttribute("subastas", subastas);


            return "paginaPrincipal";
        }
    }

    @GetMapping("/{user}/{producto}/quitarFavoritos")
    public String quitarFavoritos(@PathVariable("user")int user, @PathVariable("producto") int producto, Model model, HttpSession session){

         if (super.redirigirUsuario("Estandar", session)) {
            return "redirect:/";
        }else {

            super.getUsuarioService().eliminarProducto(user, producto);


            return "redirect:/usuario/"+user+"/paginaPrincipal";
        }
    }

    @GetMapping("/{user}/{producto}/ponerFavoritos")
    public String ponerFavoritos(@PathVariable("user")int user, @PathVariable("producto") int producto, Model model, HttpSession session){

         if (super.redirigirUsuario("Estandar", session)) {
            return "redirect:/";
        }else {

            super.getUsuarioService().insertarProducto(user, producto);

            return "redirect:/usuario/"+user+"/paginaPrincipal";
        }
    }

    @GetMapping("/{user}/{producto}/quitarComprados")
    public String quitarComprados(@PathVariable("user")int user, @PathVariable("producto") int producto, Model model, HttpSession session){

         if (super.redirigirUsuario("Estandar", session)) {
            return "redirect:/";
        }else {
            this.productoService.eliminarComprado(producto);

            return "redirect:/usuario/"+user+"/paginaPrincipal";
        }
    }

    @GetMapping("/{idUsuario}/notificaciones")
    public String notificaciones(@PathVariable("idUsuario") Integer idUsuario, Model model, HttpSession session){
        if (super.redirigirUsuario("Estandar", session)) {
            return "redirect:/";
        } else {
            UsuarioDTO usuario = super.getUsuarioService().find(idUsuario);
            List<NotificacionDTO> notificaciones = this.notificacionService.findNotificacionesByUsuario(idUsuario);

            model.addAttribute("usuario", usuario);
            model.addAttribute("notificaciones", notificaciones);

            return "verNotificaciones";
        }
    }

    @GetMapping("/eliminarNotificacion")
    public String eliminarNotificacion(@RequestParam("id") Integer idNotificacion, @RequestParam("idUsuario") Integer idUsuario, Model model, HttpSession session) {
        if (super.redirigirUsuario("Estandar", session)) {
            return "redirect:/";
        } else {

            this.notificacionService.remove(idNotificacion);

            return "redirect:/usuario/" + idUsuario + "/notificaciones";
        }
    }
}
