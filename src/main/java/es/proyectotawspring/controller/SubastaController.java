package es.proyectotawspring.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;

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
         //   SubastaDTO subasta = this.subastaService.findSubastaActiva(idProducto);
            SubastaDTO subasta = this.subastaService.findBySubastaId(10);

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





}
