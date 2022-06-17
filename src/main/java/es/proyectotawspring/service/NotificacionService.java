/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.proyectotawspring.service;

import es.proyectotawspring.dao.NotificacionRepository;
import es.proyectotawspring.dao.UsuarioRepository;
import es.proyectotawspring.dto.NotificacionDTO;
import es.proyectotawspring.dto.UsuarioDTO;
import es.proyectotawspring.entity.NotificacionEntity;
import es.proyectotawspring.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static es.proyectotawspring.entity.NotificacionEntity.toDTOList;

/**
 *
 * @author Agustín
 */
@Service
public class NotificacionService {
    private NotificacionRepository notificacionRepository;
    private UsuarioRepository usuarioRepository;

    @Autowired
    private void setNotificacionRepository(NotificacionRepository notificacionRepository){
        this.notificacionRepository = notificacionRepository;
    }

    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<NotificacionDTO> findNotificacionesByUsuario(Integer idUsuario){
        return toDTOList(notificacionRepository.findAllByDueno(this.usuarioRepository.findById(idUsuario).orElse(null)));
    }

    public void remove(int id) {
        NotificacionEntity n = this.notificacionRepository.findById(id).orElse(null);
        this.notificacionRepository.delete(n);
    }

    public void crearNotificacion(String mensaje, UsuarioDTO dueno){
        Integer id = dueno.getIdUsuario();
        UsuarioEntity usuario = this.usuarioRepository.getById(id);

        NotificacionEntity notificacion = new NotificacionEntity();
        notificacion.setDueno(usuario);
        notificacion.setTexto(mensaje);

        this.notificacionRepository.save(notificacion);
    }
}
