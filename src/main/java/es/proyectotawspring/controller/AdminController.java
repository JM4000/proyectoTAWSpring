package es.proyectotawspring.controller;

import es.proyectotawspring.Util.Busqueda;
import es.proyectotawspring.dto.ProductoDTO;
import es.proyectotawspring.dto.UsuarioDTO;
import es.proyectotawspring.service.CategoriaService;
import es.proyectotawspring.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController extends ProyectoTawController{

    private ProductoService productoService;
    public ProductoService getProductoService() {
        return productoService;
    }
    @Autowired
    public void setProductoService(ProductoService productoService) {
        this.productoService = productoService;
    }

    private CategoriaService categoriaService;
    public CategoriaService getCategoriaService() {
        return categoriaService;
    }
    @Autowired
    public void setCategoriaService(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }
    @GetMapping("/ListaProductos")
    public String listaProductos(Model model, HttpSession session) {
        if(super.redirigirUsuario("Administrador", session)){
            return "redirect:/";
        }else{
            List<ProductoDTO> productos = this.productoService.findAll();
            UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
            model.addAttribute("productos", productos);
            model.addAttribute("usuario", usuario);
            model.addAttribute("busqueda", new Busqueda());
            model.addAttribute("edit", new ProductoDTO());
            return "listaProductos";
        }
    }

    @PostMapping("/BusquedaProductos")
    public String buscarProductos(Model model, HttpSession session, @ModelAttribute("busqueda") Busqueda busqueda){
        if(super.redirigirUsuario("Administrador", session)){
            return "redirect:/";
        }else{

            List<ProductoDTO> productos;
            String like = (String) busqueda.getBusqueda();
            Integer filtro = busqueda.getFiltro();

            productos = this.productoService.findFiltered(filtro, like);

            model.addAttribute("productos", productos);
            return "listaProductos";
        }
    }
    @PostMapping("/BusquedaUsuarios")
    public String buscarUsuarios(Model model, HttpSession session, @ModelAttribute("busqueda") Busqueda busqueda){
        if(super.redirigirUsuario("Administrador", session)){
            return "redirect:/";
        }else{

            List<UsuarioDTO> usuarios;
            String like = (String) busqueda.getBusqueda();
            Integer filtro = busqueda.getFiltro();

            usuarios = super.getUsuarioService().getUsuariosLike(like, filtro);

            model.addAttribute("usuarios", usuarios);
            model.addAttribute("busqueda", new Busqueda());
            
            return "editorUsuarios";
        }
    }

    @PostMapping("/GuardarProducto")
    public String guardarProducto(@ModelAttribute("producto") ProductoDTO p, HttpSession session){
        if(super.redirigirUsuario("Administrador", session)){
            return "redirect:/";
        }else{
            this.productoService.edit(p.getIdProducto(), p.getTitulo(), p.getDescripcion(), p.getFoto(), p.getPrecioSalida(), this.categoriaService.getCategorias(p.getCategoriaList()));

            // ¿Por qué de todo lo unico que no me guarda son las categorías?

            return "redirect:/admin/ListaProductos";
        }
    }

    @GetMapping("/{id}/EditarProducto")
    public String editarProducto(Model model,HttpSession session, @PathVariable("id") Integer id){
        if(super.redirigirUsuario("Administrador", session)){
            return "redirect:/";
        }else{
            model.addAttribute("producto",this.productoService.find(id));
            model.addAttribute("categorias",this.categoriaService.findAll());
            return "editorProducto";
        }
    }

    @GetMapping("/{id}/EliminarProducto")
    public String eliminarProducto(Model model,HttpSession session, @PathVariable("id") Integer id){
        if(super.redirigirUsuario("Administrador", session)){
            return "redirect:/";
        }else{
            this.productoService.remove(id);
            return "redirect:/admin/ListaProductos";
        }
    }

    @GetMapping("/ListaUsuarios")
    public String listaUsuarios(Model model, HttpSession session){
        if(super.redirigirUsuario("Administrador", session)){
            return "redirect:/";
        }else{
            model.addAttribute("usuarios", super.getUsuarioService().findAll());
            model.addAttribute("busqueda", new Busqueda());
            return "editorUsuarios";
        }
    }
}
