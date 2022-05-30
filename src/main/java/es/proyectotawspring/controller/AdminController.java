package es.proyectotawspring.controller;

import es.proyectotawspring.dto.ProductoDTO;
import es.proyectotawspring.dto.UsuarioDTO;
import es.proyectotawspring.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @GetMapping("/ListaProductos")
    public String listaProductos(Model model, HttpSession session) {
        if(super.redirigirUsuario("Administrador", session)){
            return "redirect:/";
        }else{
            List<ProductoDTO> productos = this.productoService.findAll();
            UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
            model.addAttribute("productos", productos);
            model.addAttribute("usuario", usuario);
            return "listaProductos";
        }
    }
}
