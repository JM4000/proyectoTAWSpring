package es.proyectotawspring.controller;

import es.proyectotawspring.dto.UsuarioDTO;
import es.proyectotawspring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class GestionUsuarioController {

    protected UsuarioService usuarioService;

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @GetMapping("/")
    public String inicio() {
        return "inicioSesion";
    }

    @PostMapping("iniciarSesion")
    public String iniciarSesion(Model model, HttpSession session, @RequestParam("userName") String username, @RequestParam("inputPassword") String psw) {
        UsuarioDTO usuario = this.usuarioService.comprobarUsuario(username, psw);

        if (usuario.getIdUsuario() == null) {
            String strError = "El usuario o la clave son incorrectos";
            model.addAttribute("error", strError);
            return "inicioSesion";
        } else {
            session.setAttribute("usuario", usuario);
            session.setAttribute("tipoUsuario", usuario.getTipoUsuario().getTipoUsuario());
            switch (usuario.getTipoUsuario().getTipoUsuario()) {
                case "Administrador":
                    return "redirect:/Admin/ListaProductosServlet";
                case "Marketing":
                    return "redirect:/Marketing/ListasMarketingServlet";
                default:
                    return "redirect:/Usuario/PaginaPrincipalServlet";
            }
        }
    }
}


