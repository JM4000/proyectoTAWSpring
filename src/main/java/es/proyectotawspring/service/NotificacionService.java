/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.proyectotawspring.service;

import java.util.ArrayList;
import java.util.List;

import es.proyectotawspring.dao.NotificacionRepository;
import es.proyectotawspring.dto.NotificacionDTO;
import es.proyectotawspring.entity.NotificacionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static es.proyectotawspring.entity.NotificacionEntity.toDTOList;

/**
 *
 * @author Agustín
 */
@Service
public class NotificacionService {
    private NotificacionRepository notificacionRepository;

    @Autowired
    private void setUsuarioRepository(NotificacionRepository notificacionRepository){
        this.notificacionRepository = notificacionRepository;
    }
    
    public List<NotificacionDTO> findNotificacionesByUsuario(int idUsuario){
        return toDTOList(notificacionRepository.findAllByDueno(idUsuario));
    }

    public void remove(int id) {
        NotificacionEntity n = this.notificacionRepository.findById(id).orElse(null);
        this.notificacionRepository.delete(n);
    }
}
