/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.proyectotawspring.service;

import es.proyectotawspring.dao.SubastaRepository;
import es.proyectotawspring.dao.UsuarioRepository;
import es.proyectotawspring.dto.SubastaDTO;
import es.proyectotawspring.entity.SubastaEntity;
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

    @Autowired
    private void setUsuarioRepository(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    private SubastaRepository subastaRepository;

    @Autowired
    private void setUsuarioRepository(SubastaRepository subastaRepository){
        this.subastaRepository = subastaRepository;
    }

    
    public SubastaDTO findByProduct(int idProducto){
        SubastaEntity subasta =  (this.subastaRepository.findAllByProducto(idProducto)).orElse(null);
       
        return subasta.toDTO();
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

}
