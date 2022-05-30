/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.proyectotawspring.dto;

/**
 *
 * @author juanm
 */
public class NotificacionDTO {
    
    
    private static final long serialVersionUID = 1L;
    private Integer idNotificacion;
    private String texto;
    private UsuarioDTO dueno;

    public NotificacionDTO() {
    }

    public NotificacionDTO(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public NotificacionDTO(Integer idNotificacion, String texto) {
        this.idNotificacion = idNotificacion;
        this.texto = texto;
    }

    public Integer getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public UsuarioDTO getDueno() {
        return dueno;
    }

    public void setDueno(UsuarioDTO dueno) {
        this.dueno = dueno;
    }

}
