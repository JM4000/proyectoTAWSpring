/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.proyectotawspring.service;

import es.proyectotawspring.dao.CategoriaRepository;
import es.proyectotawspring.dao.ProductoRepository;
import es.proyectotawspring.dao.UsuarioRepository;
import es.proyectotawspring.dto.CategoriaDTO;
import es.proyectotawspring.dto.ProductoDTO;
import es.proyectotawspring.entity.CategoriaEntity;
import es.proyectotawspring.entity.ProductoEntity;
import es.proyectotawspring.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static es.proyectotawspring.entity.ProductoEntity.toDTOList;

/**
 *
 * @author juanm
 * hasta edit incluido
 * @autor Chris
 * los 3 ultimos
 */
@Service
public class
ProductoService {

    private UsuarioRepository usuarioRepository;
    private CategoriaRepository categoriaRepository;

    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }

    public CategoriaRepository getCategoriaRepository() {
        return categoriaRepository;
    }

    @Autowired
    public void setCategoriaRepository(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public ProductoRepository getProductoRepository() {
        return productoRepository;
    }

    @Autowired
    public void setProductoRepository(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Autowired
    private void setUsuarioRepository(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    private ProductoRepository productoRepository;

    @Autowired
    private void setUsuarioRepository(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    private CategoriaService categoriaService;

    @Autowired
    public void setCategoriaService(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    public List<ProductoDTO> findFiltered(Integer filtro, String like) {

        List<ProductoDTO> productos;
        if (like != null && !like.isEmpty()) {

            switch (filtro) {
                case (1):
                    return toDTOList(this.productoRepository.findAllByTituloLike(like).orElse(null));

                default:
                    return toDTOList(this.productoRepository.findByCategoriaLike(like).orElse(null));

            }

        } else {
            productos = toDTOList(this.productoRepository.findAll());
        }

        return productos;
    }

    public ProductoDTO find(int id) {
        return this.productoRepository.getById(id).toDTO();
    }
    
    public List<ProductoDTO> findAll() {
        return toDTOList(this.productoRepository.findAll());
    }

    public void remove(int id) {
        this.productoRepository.delete(this.productoRepository.findById(id).orElse(null));
    }

    public void borrar(Integer idProducto){this.productoRepository.delete(this.productoRepository.findByIdProducto(idProducto));}

    public void edit(int id,String title, String desc, String foto, double precio, List<CategoriaEntity> categoriasFinales) {
        ProductoEntity producto = this.productoRepository.findById(id).orElse(null);
              
        producto.setTitulo(title);
        producto.setDescripcion(desc);
        producto.setFoto(foto);
        producto.setPrecioSalida(precio);
        producto.setCategoriaList(categoriasFinales);
        
        this.productoRepository.save(producto);

        this.categoriaService.modificarCategorias(categoriasFinales, this.categoriaService.getCategoriasRestantes(categoriasFinales), producto);
        
    }


    public Boolean isProductFavourite(int idProducto, int idUsuario){
        return this.productoRepository.isProductFavourite(idUsuario,idProducto).isPresent();
     }

    public void eliminarComprado(Integer idProducto) {
       
       ProductoEntity producto =  this.productoRepository.findById(idProducto).orElse(null);
       producto.setIdComprador(null);
       
    }
     public void añadirComprado(Integer idUsuario, Integer idProducto) {
       UsuarioEntity usuario = this.usuarioRepository.findById(idUsuario).orElse(null);
       ProductoEntity producto =  this.productoRepository.findById(idProducto).orElse(null);
       producto.setIdComprador(usuario);
       
       this.productoRepository.save(producto);
       
    }

    public List<ProductoDTO> getProductosVendedor(int idUsuario){
        return toDTOList(this.productoRepository.getProductosVendedor(idUsuario).orElse(null));
    }


    public void editar (int idProducto,String titulo, String descripcion, String foto, List<CategoriaDTO> categoriasFinales){
        ProductoEntity producto = this.productoRepository.findById(idProducto).orElse(null);

        producto.setTitulo(titulo);
        producto.setDescripcion(descripcion);
        producto.setFoto(foto);




        List<CategoriaEntity> categoriaEntities= new ArrayList<>();
        for(CategoriaDTO c : categoriasFinales){
            CategoriaEntity cat= this.categoriaRepository.findById(c.getIdCategoria()).orElse(null);
            categoriaEntities.add(cat);
        }

        producto.setCategoriaList(categoriaEntities);
        this.productoRepository.save(producto);
    }
  
     
}
