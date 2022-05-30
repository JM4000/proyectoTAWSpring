package es.proyectotawspring.controller;

import es.proyectotawspring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

public abstract class ProyectoTawController extends HttpServlet {

    private UsuarioService usuarioService;
    public UsuarioService getCustomerService() {
        return usuarioService;
    }
    @Autowired
    public void setCustomerService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    protected Boolean redirigirUsuario (String tipoUsuario, HttpSession sesion){
        if (!this.usuarioService.comprobarPermisos(sesion, tipoUsuario)){
            return true;
        }else{
            return false;
        }

    }
}
