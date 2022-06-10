/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.proyectotawspring.service;

import es.proyectotawspring.dao.GeneroRepository;
import es.proyectotawspring.dao.ProductoRepository;
import es.proyectotawspring.dao.TipoUsuarioRepository;
import es.proyectotawspring.dao.UsuarioRepository;
import es.proyectotawspring.dto.UsuarioDTO;
import es.proyectotawspring.entity.ProductoEntity;
import es.proyectotawspring.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

import static es.proyectotawspring.entity.UsuarioEntity.toDTOList;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    @Autowired
    private void setUsuarioRepository(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    private GeneroRepository generoRepository;

    @Autowired
    public void setGeneroRepository(GeneroRepository generoRepository){
        this.generoRepository = generoRepository;
    }

    private TipoUsuarioRepository tipoUsuarioRepository;


    @Autowired
    public void setTipoUsuarioRepository(TipoUsuarioRepository tipoUsuarioRepository){
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }


    private ProductoRepository productoRepository;
    @Autowired
    public void setProductoRepository(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
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
        usuario.setGenero(this.generoRepository.findById(gender).orElse(null));
        usuario.setTipoUsuario(this.tipoUsuarioRepository.findById(userType).orElse(null));

        this.usuarioRepository.save(usuario);

    }

    public void remove(int id) {
        this.usuarioRepository.deleteById(id);
    }
/* No tiene sentido esta función
    public void edit(int id) {
        this.usuarioRepository.save((this.usuarioRepository).findById(id));
    }
*/
    public List<UsuarioDTO> getUsuariosLike(String like, Integer filtro) {
        List<UsuarioDTO> usuarios;

        if (like != null) {
            switch (filtro) {
                case (2): //nombre
                    return toDTOList(this.usuarioRepository.findAllByNombreLike(like));
                case (3)://apellido
                    return toDTOList(this.usuarioRepository.findAllByApellidosLike(like));
                case (4)://domicilio
                    return toDTOList(this.usuarioRepository.findAllByDomicilioLike(like));
                case (5)://ciudad
                    return toDTOList(this.usuarioRepository.findAllByCiudadLike(like));
                case (6)://edad
                    return toDTOList(this.usuarioRepository.findAllByEdad(Integer.parseInt(like)));
                case (7)://genero
                    return toDTOList(this.usuarioRepository.findAllByGeneroLike(like));
                case (8)://tipoUsuario
                    return toDTOList(this.usuarioRepository.findAllByTipoUsuarioLike(like));
                default://nombreUsuario
                    return toDTOList(this.usuarioRepository.findAllByNombreUsuarioLike(like));
            }
        } else {
            usuarios = toDTOList(this.usuarioRepository.findAll());
        }

        return usuarios;
    }

    public UsuarioDTO find(int id) {

        return this.usuarioRepository.findById(id).orElse(null).toDTO();
    }

   /* public void insertarProducto(int idUsuario, int idProducto) {
        ProductoEntity producto = this.pFacade.find(idProducto);
        UsuarioEntity usuario = this.usuarioRepository.findById(idUsuario).orElse(null);

        if (!this.pFacade.isProductFavourite(idUsuario, idProducto)) {
            List<UsuarioEntity> usuarios = producto.getUsuarioList();
            List<ProductoEntity> productos = usuario.getProductoList();

            productos.add(producto);
            usuarios.add(usuario);

            producto.setUsuarioList(usuarios);
            usuario.setProductoList(productos);

            this.usuarioRepository.save(usuario);
            this.pFacade.edit(producto);
        }

    }

    public void eliminarProducto(int idUsuario, int idProducto) {
        ProductoEntity producto = this.pFacade.find(idProducto);
        UsuarioEntity usuario = this.usuarioRepository.findById(idUsuario).orElse(null);

        if (this.pFacade.isProductFavourite(idUsuario, idProducto)) {
            List<UsuarioEntity> usuarios = producto.getUsuarioList();
            List<ProductoEntity> productos = usuario.getProductoList();

            productos.remove(producto);
            usuarios.remove(usuario);

            this.usuarioRepository.save(usuario);
            this.pFacade.edit(producto);
        }

    }
*/
    public UsuarioDTO comprobarUsuario(String username, String psw) {
        return this.usuarioRepository.findUsuarioByNombreUsuarioAndContrasena(username, psw).orElse(null).toDTO();
    }

    public List<UsuarioDTO> findAll() {
        return toDTOList(this.usuarioRepository.findAll());
    }
    
    public boolean comprobarPermisos(HttpSession sesion, String usuario){
        String sesionReal = (String) sesion.getAttribute("tipoUsuario");
        return usuario.equals(sesionReal);
    }

    public void editarUsuario(int idUsuario,String username, String pass, String name, String surname, String city, String address, int age, String gender, String userType) {
        UsuarioEntity usuario = this.usuarioRepository.findById(idUsuario).orElse(null);

        usuario.setNombreUsuario(username);
        usuario.setContrasena(pass);
        usuario.setNombre(name);
        usuario.setApellidos(surname);
        usuario.setCiudad(city);
        usuario.setDomicilio(address);
        usuario.setEdad(age);
        usuario.setGenero(this.generoRepository.findById(gender).orElse(null));
        usuario.setTipoUsuario(this.tipoUsuarioRepository.findById(userType).orElse(null));

        this.usuarioRepository.save(usuario);
    }

    public void save(UsuarioDTO us) {
        UsuarioEntity u = new UsuarioEntity();

        u.setTipoUsuario(this.tipoUsuarioRepository.findById(us.getTipoUsuario().getTipoUsuario()).orElse(null));
        u.setGenero(this.generoRepository.findById(us.getGenero().getGenero()).orElse(null));
        u.setEdad(us.getEdad());
        u.setDomicilio(us.getDomicilio());
        u.setCiudad(us.getCiudad());
        u.setApellidos(us.getApellidos());
        u.setNombre(us.getNombre());
        u.setContrasena(us.getContrasena());
        u.setIdUsuario(us.getIdUsuario());
        u.setNombreUsuario(us.getNombre());

        this.usuarioRepository.save(u);
    }
    public Boolean isProductFavourite(int idProducto, int idUsuario){
        return this.productoRepository.isProductFavourite(idUsuario,idProducto).isPresent();
    }
    public void eliminarProducto(int idUsuario, int idProducto) {
        ProductoEntity producto = this.productoRepository.findById(idProducto).orElse(null);
        UsuarioEntity usuario = this.usuarioRepository.findById(idUsuario).orElse(null);

        if (isProductFavourite(idProducto,idUsuario)) { //si el producto  es favorito del usuario
            List<UsuarioEntity> usuarios = producto.getUsuarioList();
            List<ProductoEntity> productos = usuario.getProductoList();

            productos.remove(producto);
            usuarios.remove(usuario);

            this.usuarioRepository.save(usuario);
            this.productoRepository.save(producto);
        }
    }

    public void insertarProducto(int idUsuario, int idProducto) {
        ProductoEntity producto = this.productoRepository.findById(idProducto).orElse(null);
        UsuarioEntity usuario = this.usuarioRepository.findById(idUsuario).orElse(null);
        List <ProductoEntity> isFavourite = this.productoRepository.isProductFavourite(idUsuario, idProducto).orElse(null);
        if (!isProductFavourite(idProducto,idUsuario)) { //si el producto no es favorito del usuario
            List<UsuarioEntity> usuarios = producto.getUsuarioList();
            List<ProductoEntity> productos = usuario.getProductoList();

            productos.add(producto);
            usuarios.add(usuario);
            
            producto.setUsuarioList(usuarios);
            usuario.setProductoList(productos);

            this.usuarioRepository.save(usuario);
            this.productoRepository.save(producto);
        }

    }

}
