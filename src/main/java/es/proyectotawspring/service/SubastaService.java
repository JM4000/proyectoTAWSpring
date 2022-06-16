/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.proyectotawspring.service;

import es.proyectotawspring.dao.ProductoRepository;
import es.proyectotawspring.dao.SubastaRepository;
import es.proyectotawspring.dao.UsuarioRepository;
import es.proyectotawspring.dto.SubastaDTO;
import es.proyectotawspring.dto.UsuarioDTO;
import es.proyectotawspring.entity.ProductoEntity;
import es.proyectotawspring.entity.SubastaEntity;
import es.proyectotawspring.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static es.proyectotawspring.entity.SubastaEntity.toDTOList;

/**
 *
 * @author Chris
 */
@Service
public class SubastaService {
    

    private UsuarioRepository usuarioRepository;
    private ProductoRepository productoRepository;

    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }

    public ProductoRepository getProductoRepository() {
        return productoRepository;
    }

    @Autowired
    public void setProductoRepository(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public SubastaRepository getSubastaRepository() {
        return subastaRepository;
    }
    @Autowired
    public void setSubastaRepository(SubastaRepository subastaRepository) {
        this.subastaRepository = subastaRepository;
    }

    @Autowired
    private void setUsuarioRepository(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    private SubastaRepository subastaRepository;

    @Autowired
    private void setUsuarioRepository(SubastaRepository subastaRepository){
        this.subastaRepository = subastaRepository;
    }


    public SubastaDTO findBySubastaId(Integer idSubasta){
        SubastaEntity subasta = this.subastaRepository.findByIdSubasta(idSubasta);
        return subasta.toDTO();
    }
    public SubastaDTO findByProduct(int idProducto){
        SubastaEntity subasta =  (this.subastaRepository.findAllByProducto(idProducto)).orElse(null);
       
        return subasta.toDTO();
    }
    public SubastaDTO findSubastaActiva(Integer idProducto){
        SubastaEntity subastas = this.subastaRepository.findByProductoIdProducto(idProducto);
      return subastas.toDTO();

    }

    
      public List<SubastaDTO> SubastaActiva(String titulo, String categoria){
        List <SubastaEntity> result = this.subastaRepository.findSubastaActivaFiltro(new Date(),"%"+titulo+"%","%"+categoria+"%").orElse(null);
        return toDTOList(result);
    }
    public List <SubastaDTO> SubastaProductosFavoritos (int id, String titulo,String categoria){
         List <SubastaEntity> result = this.subastaRepository.findSubastaWithFavouriteProductList(new Date(),id,"%"+titulo+"%","%"+categoria+"%").orElse(null);
        return toDTOList(result);
    }
     public List <SubastaDTO> SubastaProductosComprados (int id, String titulo,String categoria){
         
         List <SubastaEntity> result = this.subastaRepository.findSubastaWithProductsComprados(this.usuarioRepository.findById(id).orElse(null), "%"+titulo+"%","%"+categoria+"%").orElse(null);
        return toDTOList(result);
    }
     
      public SubastaDTO find(int id) {
         SubastaEntity subasta = this.subastaRepository.findById(id).orElse(null);
        return subasta.toDTO();
    }

    public void editarCierreSubasta(SubastaDTO ss, Date d){
        SubastaEntity s = this.subastaRepository.findByIdSubasta(ss.getIdSubasta());
        s.setFechaCierre(d);
        this.subastaRepository.save(s);
    }


    public void editarCompradorSubasta(Integer idProducto,Integer idMayorPostor) {
        ProductoEntity p = this.productoRepository.findByIdProducto(idProducto);
        UsuarioEntity u = this.usuarioRepository.findById(idMayorPostor).orElse(null);
        p.setIdComprador(u);
        this.productoRepository.save(p);
    }


    public void crearSubasta(Date fecha, Integer idProducto, double precioSalida,int idUser) {
        SubastaEntity subasta = new SubastaEntity();


        UsuarioEntity user = this.usuarioRepository.findById(idUser).orElse(null);
        ProductoEntity producto = this.productoRepository.findByIdProducto(idProducto);

        SubastaEntity s = new SubastaEntity();
        s.setCreador(user);
        s.setPredioActual(precioSalida);
        s.setProducto(producto);
        s.setFechaCierre(fecha);
        this.subastaRepository.save(s);

    }



    public void guardarPuja(Integer idSubasta,Double precioPujar, Integer idMayorPostor){
         SubastaEntity subasta = this.subastaRepository.findByIdSubasta(idSubasta);
         UsuarioEntity usuario = this.usuarioRepository.findById(idMayorPostor).orElse(null);

         subasta.setPredioActual(precioPujar);
         subasta.setMayorPostor(usuario);
         this.subastaRepository.save(subasta);

    }

}
