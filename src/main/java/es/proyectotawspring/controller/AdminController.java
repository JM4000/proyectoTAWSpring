package es.proyectotawspring.controller;

import es.proyectotawspring.Util.Busqueda;
import es.proyectotawspring.dto.ProductoDTO;
import es.proyectotawspring.dto.UsuarioDTO;
import es.proyectotawspring.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

            System.out.println(busqueda.toString());

            List<ProductoDTO> productos;
            String like = (String) busqueda.getBusqueda();
            Integer filtro = busqueda.getFiltro();

            productos = this.productoService.findFiltered(filtro, like);

            model.addAttribute("productos", productos);
            return "listaProductos";
        }
    }
}
