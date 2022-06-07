package es.proyectotawspring.controller;

import es.proyectotawspring.dto.ProductoDTO;
import es.proyectotawspring.dto.UsuarioDTO;
import es.proyectotawspring.service.ProductoService;
import es.proyectotawspring.service.SubastaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("subasta")
public class SubastaController extends ProyectoTawController{
    private ProductoService productoService;
    private SubastaService subastaService;

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
    public String doEditarSubasta(){
        return "/";
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



}
