package es.proyectotawspring.controller;


import es.proyectotawspring.dto.ProductoDTO;
import es.proyectotawspring.dto.UsuarioDTO;
import es.proyectotawspring.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("usuario")
public class UsuarioController extends ProyectoTawController {
    private ProductoService productoService;

    @Autowired
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



}
