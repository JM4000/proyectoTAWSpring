package es.proyectotawspring.controller;

import es.proyectotawspring.Util.Busqueda;
import es.proyectotawspring.Util.BusquedaParaLista;
import es.proyectotawspring.dto.*;
import es.proyectotawspring.service.CategoriaService;
import es.proyectotawspring.service.ListaService;
import es.proyectotawspring.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("marketing")
public class MarketingController extends ProyectoTawController{
    private ListaService listaService;
    private CategoriaService categoriaService;
    private NotificacionService notificacionService;

    public ListaService getListaService() {
        return listaService;
    }

    @Autowired
    public void setListaService(ListaService listaService) {
        this.listaService = listaService;
    }

    public CategoriaService getCategoriaService() {
        return categoriaService;
    }

    @Autowired
    public void setCategoriaService(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    public NotificacionService getNotificacionService() {
        return notificacionService;
    }

    @Autowired
    public void setNotificacionService(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @GetMapping("/listasMarketing")
    public String mostrarListas(Model model, HttpSession session){
        if (super.redirigirUsuario("Marketing", session)) {
            return "redirect:/";
        } else {
            List<ListaDTO> listas = this.listaService.findAll();
            List<CategoriaDTO> categorias = this.categoriaService.findAll();

            model.addAttribute("listas", listas);
            model.addAttribute("categorias", categorias);
            model.addAttribute("busqueda", new Busqueda());

            return "listasMarketing";
        }

    }

    @PostMapping("/busquedaListas")
    public String busquedaListas(Model model, HttpSession session, @ModelAttribute("busqueda") Busqueda busqueda){
        if (super.redirigirUsuario("Marketing", session)) {
            return "redirect:/";
        } else {
            List<ListaDTO> listas = this.listaService.getListasLike(busqueda.getBusqueda());
            List<CategoriaDTO> categorias = this.categoriaService.findAll();

            model.addAttribute("listas", listas);
            model.addAttribute("categorias", categorias);
            model.addAttribute("busqueda", new Busqueda());

            return "listasMarketing";
        }
    }

    @PostMapping("/nuevaLista")
    public String nuevaLista(HttpSession session, @ModelAttribute("nombre") String nombre, @ModelAttribute("categoria") String categoria){
        if (super.redirigirUsuario("Marketing", session)) {
            return "redirect:/";
        } else {
            this.listaService.crearLista(nombre, categoria);

            return "redirect:/marketing/listasMarketing";
        }
    }

    @GetMapping("/{idlista}/verLista")
    public String verLista(@PathVariable("idlista") Integer idlista, Model model, HttpSession session){
        if (super.redirigirUsuario("Marketing", session)) {
            return "redirect:/";
        } else {
            ListaDTO lista = this.listaService.find(idlista);
            List<UsuarioDTO> usuarios = lista.getusuarioList();
            BusquedaParaLista busqueda = new BusquedaParaLista();
            busqueda.setIdlista(idlista);

            model.addAttribute("lista", lista);
            model.addAttribute("usuarios", usuarios);
            model.addAttribute("busqueda", busqueda);

            return "editorLista";
        }

    }

    @PostMapping("/BusquedaUsuarios")
    public String buscarUsuarios(Model model, HttpSession session, @ModelAttribute("busqueda") BusquedaParaLista busqueda) {
        if (super.redirigirUsuario("Marketing", session)) {
            return "redirect:/";
        } else {

            List<UsuarioDTO> usuarios;
            String like = (String) busqueda.getBusqueda();
            Integer filtro = busqueda.getFiltro();
            Integer idlista = busqueda.getIdlista();

            ListaDTO lista = this.listaService.find(idlista);

            if (like == "" || like == null) {
                usuarios = lista.getusuarioList();
            } else {
                usuarios = super.getUsuarioService().getUsuariosLike(like, filtro);
            }

            model.addAttribute("lista", lista);
            model.addAttribute("usuarios", usuarios);
            model.addAttribute("busqueda", busqueda);

            return "editorLista";
        }
    }

    @GetMapping("/modificarLista")
    public String modificarLista(Model model, HttpSession session, @RequestParam("edit") String edit, @RequestParam("id") Integer id){
        if (super.redirigirUsuario("Marketing", session)) {
            return "redirect:/";
        } else {

            this.listaService.editarLista(id, edit);

            return "redirect:/marketing/listasMarketing";
        }
    }

    @GetMapping("/{id}/eliminarLista")
    public String eliminarLista(HttpSession session, @PathVariable("id") Integer id){
        if (super.redirigirUsuario("Marketing", session)) {
            return "redirect:/";
        } else {

            this.listaService.remove(id);

            return "redirect:/marketing/listasMarketing";
        }
    }

    @PostMapping("/notificarLista/{idlista}")
    public String notificarLista(HttpSession session, Model model, @RequestParam("mensaje") String mensaje, @PathVariable("idlista") Integer idlista){
        if (super.redirigirUsuario("Marketing", session)) {
            return "redirect:/";
        } else {
            ListaDTO lista = this.listaService.find(idlista);
            List<UsuarioDTO> usuarios = lista.getusuarioList();

            NotificacionDTO notificacionDTO;

            for (UsuarioDTO usuario : usuarios) {
                this.notificacionService.crearNotificacion(mensaje, usuario);
            }

            return "redirect:/marketing/listasMarketing";
        }
    }
}
