package es.proyectotawspring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("subasta")
public class SubastaController {


    @GetMapping("/{idProducto}/EditarSubasta")
    public String doEditarSubasta(){
        return "/";
    }

    @GetMapping("/{idProducto}/EliminarSubasta")
    public String doBorrarSubasta(){
            return "/";
    }



}
