/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.proyectotawspring.service;

import es.proyectotawspring.dao.GeneroRepository;
import es.proyectotawspring.dto.GeneroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static es.proyectotawspring.entity.GeneroEntity.toDTOList;

/**
 *
 * @author juanm
 */
@Service
public class GeneroService {

    private GeneroRepository generoRepository;

    @Autowired
    public void setGeneroRepository(GeneroRepository generoRepository){
        this.generoRepository = generoRepository;
    }
    
    public List<GeneroDTO> findAll() {
        return toDTOList(this.generoRepository.findAll());
    }


}
