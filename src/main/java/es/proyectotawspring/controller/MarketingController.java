package es.proyectotawspring.controller;

import es.proyectotawspring.Util.Busqueda;
import es.proyectotawspring.Util.BusquedaParaLista;
import es.proyectotawspring.dto.CategoriaDTO;
import es.proyectotawspring.dto.ListaDTO;
import es.proyectotawspring.dto.ProductoDTO;
import es.proyectotawspring.dto.UsuarioDTO;
import es.proyectotawspring.entity.ListaEntity;
import es.proyectotawspring.service.CategoriaService;
import es.proyectotawspring.service.ListaService;
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

            model.addAttribute("lista", lista);
            BusquedaParaLista busqueda = new BusquedaParaLista();
            busqueda.setIdlist(idlista);
            model.addAttribute("busqueda", busqueda);

            return "editorLista";
        }

    }

    @PostMapping("/BusquedaUsuarios")
    public String buscarUsuarios(Model model, HttpSession session, @ModelAttribute("busqueda") Busqueda busqueda) {
        if (super.redirigirUsuario("Marketing", session)) {
            return "redirect:/";
        } else {

            List<UsuarioDTO> usuarios;
            String like = (String) busqueda.getBusqueda();
            Integer filtro = busqueda.getFiltro();

            usuarios = super.getUsuarioService().getUsuariosLike(like, filtro);


            //model.addAttribute("lista", );
            model.addAttribute("usuarios", usuarios);
            model.addAttribute("busqueda", busqueda);

            return "editorLista";
        }
    }
}
