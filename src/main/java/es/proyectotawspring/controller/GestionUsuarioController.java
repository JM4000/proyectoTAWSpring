package es.proyectotawspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GestionUsuarioController {
    @GetMapping("/")
    public String inicio(){
        return "inicioSesion";
    }
}
