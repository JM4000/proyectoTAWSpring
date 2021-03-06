/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.proyectotawspring.service;

import es.proyectotawspring.dao.TipoUsuarioRepository;
import es.proyectotawspring.dto.TipousuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static es.proyectotawspring.entity.TipousuarioEntity.toDTOList;

/**
 *
 * @author juanm
 */
@Service
public class TipousuarioService {

    private TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    public void setTipoUsuarioRepository(TipoUsuarioRepository tipoUsuarioRepository){
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }
    
    public List<TipousuarioDTO> findAll() {
        return toDTOList(this.tipoUsuarioRepository.findAll());
    }
  
}
