package es.proyectotawspring.controller;

import es.proyectotawspring.Util.Busqueda;
import es.proyectotawspring.dto.*;
import es.proyectotawspring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController extends ProyectoTawController {

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
        if (super.redirigirUsuario("Administrador", session)) {
            return "redirect:/";
        } else {
            List<ProductoDTO> productos = this.productoService.findAll();
            UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
            model.addAttribute("productos", productos);
            model.addAttribute("usuario", usuario);
            model.addAttribute("busqueda", new Busqueda());
            model.addAttribute("edit", new ProductoDTO());
            return "listaProductos";
        }
    }

    private GeneroService generoService;

    public GeneroService getGeneroService() {
        return generoService;
    }
    @Autowired
    public void setGeneroService(GeneroService generoService) {
        this.generoService = generoService;
    }

    public TipousuarioService tipousuarioService;

    public TipousuarioService getTipousuarioService() {
        return tipousuarioService;
    }
    @Autowired
    public void setTipousuarioService(TipousuarioService tipousuarioService) {
        this.tipousuarioService = tipousuarioService;
    }

    @PostMapping("/BusquedaProductos")
    public String buscarProductos(Model model, HttpSession session, @ModelAttribute("busqueda") Busqueda busqueda) {
        if (super.redirigirUsuario("Administrador", session)) {
            return "redirect:/";
        } else {

            List<ProductoDTO> productos;
            String like = (String) busqueda.getBusqueda();
            Integer filtro = busqueda.getFiltro();

            productos = this.productoService.findFiltered(filtro, like);

            model.addAttribute("productos", productos);
            return "listaProductos";
        }
    }

    @PostMapping("/BusquedaUsuarios")
    public String buscarUsuarios(Model model, HttpSession session, @ModelAttribute("busqueda") Busqueda busqueda) {
        if (super.redirigirUsuario("Administrador", session)) {
            return "redirect:/";
        } else {

            List<UsuarioDTO> usuarios;
            String like = (String) busqueda.getBusqueda();
            Integer filtro = busqueda.getFiltro();

            usuarios = super.getUsuarioService().getUsuariosLike(like, filtro);

            model.addAttribute("usuarios", usuarios);
            model.addAttribute("busqueda", busqueda);

            return "editorUsuarios";
        }
    }

    @PostMapping("/GuardarProducto")
    public String guardarProducto(@ModelAttribute("producto") ProductoDTO p, HttpSession session) {
        if (super.redirigirUsuario("Administrador", session)) {
            return "redirect:/";
        } else {
            this.productoService.edit(p.getIdProducto(), p.getTitulo(), p.getDescripcion(), p.getFoto(), p.getPrecioSalida(), this.categoriaService.getCategorias(p.getCategoriaList()));

            // ¿Por qué de todo lo unico que no me guarda son las categorías?

            return "redirect:/admin/ListaProductos";
        }
    }

    @GetMapping("/{id}/EditarProducto")
    public String editarProducto(Model model, HttpSession session, @PathVariable("id") Integer id) {
        if (super.redirigirUsuario("Administrador", session)) {
            return "redirect:/";
        } else {
            model.addAttribute("producto", this.productoService.find(id));
            model.addAttribute("categorias", this.categoriaService.findAll());
            return "editorProducto";
        }
    }

    @GetMapping("/{id}/EliminarProducto")
    public String eliminarProducto(Model model, HttpSession session, @PathVariable("id") Integer id) {
        if (super.redirigirUsuario("Administrador", session)) {
            return "redirect:/";
        } else {
            this.productoService.remove(id);
            return "redirect:/admin/ListaProductos";
        }
    }

    @GetMapping("/ListaUsuarios")
    public String listaUsuarios(Model model, HttpSession session) {
        if (super.redirigirUsuario("Administrador", session)) {
            return "redirect:/";
        } else {
            model.addAttribute("usuarios", super.getUsuarioService().findAll());
            model.addAttribute("busqueda", new Busqueda());
            return "editorUsuarios";
        }
    }

    @GetMapping("/{id}/EliminarUsuario")
    public String banHammer(@PathVariable("id") Integer id, HttpSession session) {
        if (super.redirigirUsuario("Administrador", session)) {
            return "redirect:/";
        } else {
            super.getUsuarioService().remove(id);
            return "redirect:/admin/ListaUsuarios";
        }
    }

    @GetMapping("/{id}/EditarUsuario")
    public String editarUsuario(@PathVariable("id") Integer id, HttpSession session, Model model) {
        if (super.redirigirUsuario("Administrador", session)) {
            return "redirect:/";
        } else {
            List<GeneroDTO> generos = this.generoService.findAll();
            List<TipousuarioDTO> tipos = this.tipousuarioService.findAll();
            UsuarioDTO usuario = null;
            if(id!=-1){
                super.getUsuarioService().find(id);
            }else{
                usuario = new UsuarioDTO();
            }

            model.addAttribute("usuario", usuario);
            model.addAttribute("tiposUsuario", tipos);
            model.addAttribute("generos", generos);

            return "editorUsuario";
        }
    }

    @PostMapping("/GuardarUsuario")
    public String guardarUsuario(HttpSession session, @ModelAttribute("usuario") UsuarioDTO usuario){
        if (super.redirigirUsuario("Administrador", session)) {
            return "redirect:/";
        } else {

            super.getUsuarioService().save(usuario);

            return "redirect:/admin/ListaUsuarios";
        }
    }

    @GetMapping("/EditorCategorias")
    public String editorCategorias(Model model, HttpSession session){
        if (super.redirigirUsuario("Administrador", session)) {
            return "redirect:/";
        } else {

            model.addAttribute("categorias", this.categoriaService.findAll());
            model.addAttribute("nuevo",new CategoriaDTO());
            return "editorCategorias";
        }
    }

    @PostMapping("/NuevaCategoria")
    public String nuevaCategoria(Model model, HttpSession session,@ModelAttribute("nuevo") CategoriaDTO cat){
        if (super.redirigirUsuario("Administrador", session)) {
            return "redirect:/";
        } else {

            this.categoriaService.crearCategoria(cat.getNombre());

            return "redirect:/admin/EditorCategorias";
        }
    }

    @PostMapping("/EditarCategoria")
    public String editarCategoria(Model model, HttpSession session, @RequestParam("edit") String edit, @RequestParam("id") Integer id){
        if (super.redirigirUsuario("Administrador", session)) {
            return "redirect:/";
        } else {

            this.categoriaService.editarCategoria(id, edit);

            return "redirect:/admin/EditorCategorias";
        }
    }

    @GetMapping("/{id}/EliminarCategoria")
    public String eliminarCategoria(HttpSession session, @PathVariable("id") Integer id){
        if (super.redirigirUsuario("Administrador", session)) {
            return "redirect:/";
        } else {

            this.categoriaService.remove(id);

            return "redirect:/admin/EditorCategorias";
        }
    }
}
