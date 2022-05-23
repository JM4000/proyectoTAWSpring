/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.proyectotawspring.service;

import es.proyectotawspring.dao.GeneroRepository;
import es.proyectotawspring.dao.TipoUsuarioRepository;
import es.proyectotawspring.dao.UsuarioRepository;
import es.proyectotawspring.dto.UsuarioDTO;
import es.proyectotawspring.entity.ProductoEntity;
import es.proyectotawspring.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    private GeneroRepository generoRepository;

    public GeneroRepository getGeneroRepository() {
        return generoRepository;
    }

    @Autowired
    public void setGeneroRepository(GeneroRepository generoRepository){
        this.generoRepository = generoRepository;
    }

    private TipoUsuarioRepository tipoUsuarioRepository;

    public TipoUsuarioRepository getTipoUsuarioRepository() {
        return tipoUsuarioRepository;
    }

    @Autowired
    public void setTipoUsuarioRepository(TipoUsuarioRepository tipoUsuarioRepository){
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    public List<UsuarioDTO> toDTOList(List<UsuarioEntity> lista) {

        List<UsuarioDTO> result = null;

        if (lista != null) {
            result = new ArrayList<>();
            for (UsuarioEntity c : lista) {
                result.add(c.toDTO());
            }
        }

        return result;
    }

    public void crearUsuario(String username, String pass, String name, String surname, String city, String address, Integer age, String gender, String userType) {

        UsuarioEntity usuario = new UsuarioEntity();

        usuario.setNombreUsuario(username);
        usuario.setContrasena(pass);
        usuario.setNombre(name);
        usuario.setApellidos(surname);
        usuario.setCiudad(city);
        usuario.setDomicilio(address);
        usuario.setEdad(age);
        usuario.setGenero(this.generoRepository.findById(gender).toString());
        usuario.setTipoUsuario(this.tipoUsuarioRepository.findById(userType).toString());

        this.usuarioRepository.save(usuario);

    }

    public void remove(int id) {
        this.usuarioRepository.deleteById(id);
    }
/* ?????????
    public void edit(int id) {
        this.uFacade.edit((this.uFacade).find(id));
    }
*/
    public List<UsuarioDTO> getCategoriasLike(String like, Integer filtro) {
        List<UsuarioDTO> usuarios;

        if (like != null) {
            usuarios = this.toDTOList(this.uFacade.findFiltered(filtro, like));
        } else {
            usuarios = this.toDTOList(this.uFacade.findAll());
        }

        return usuarios;
    }

    public UsuarioDTO find(int id) {

        return this.uFacade.find(id).toDTO();
    }

    public void insertarProducto(int idUsuario, int idProducto) {
        ProductoEntity producto = this.pFacade.find(idProducto);
        UsuarioEntity usuario = this.uFacade.find(idUsuario);

        if (!this.pFacade.isProductFavourite(idUsuario, idProducto)) {
            List<UsuarioEntity> usuarios = producto.getUsuarioList();
            List<ProductoEntity> productos = usuario.getProductoList();

            productos.add(producto);
            usuarios.add(usuario);

            producto.setUsuarioList(usuarios);
            usuario.setProductoList(productos);

            this.uFacade.edit(usuario);
            this.pFacade.edit(producto);
        }

    }

    public void eliminarProducto(int idUsuario, int idProducto) {
        ProductoEntity producto = this.pFacade.find(idProducto);
        UsuarioEntity usuario = this.uFacade.find(idUsuario);

        if (this.pFacade.isProductFavourite(idUsuario, idProducto)) {
            List<UsuarioEntity> usuarios = producto.getUsuarioList();
            List<ProductoEntity> productos = usuario.getProductoList();

            productos.remove(producto);
            usuarios.remove(usuario);

            this.uFacade.edit(usuario);
            this.pFacade.edit(producto);
        }

    }

    public UsuarioDTO comprobarUsuario(String username, String psw) {
        return this.uFacade.comprobarUsuario(username, psw).toDTO();
    }

    public List<UsuarioDTO> findAll() {
        return this.toDTOList(this.uFacade.findAll());
    }
    
    public boolean comprobarPermisos(HttpSession sesion, String usuario){
        String sesionReal = (String) sesion.getAttribute("tipoUsuario");
        return usuario.equals(sesionReal);
    }

    public void editarUsuario(int idUsuario,String username, String pass, String name, String surname, String city, String address, int age, String gender, String userType) {
        UsuarioEntity usuario = this.uFacade.find(idUsuario);

        usuario.setNombreUsuario(username);
        usuario.setContrasena(pass);
        usuario.setNombre(name);
        usuario.setApellidos(surname);
        usuario.setCiudad(city);
        usuario.setDomicilio(address);
        usuario.setEdad(age);
        usuario.setGenero(this.gFacade.find(gender));
        usuario.setTipoUsuario(this.tuFacade.find(userType));

        this.uFacade.edit(usuario);
    }
}
