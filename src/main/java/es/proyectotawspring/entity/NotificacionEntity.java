/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.proyectotawspring.entity;

import es.proyectotawspring.dto.NotificacionDTO;
import es.proyectotawspring.dto.SubastaDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author juanm
 * casi todo
 * @autor Agustín
 * toDTO
 */
@Entity
@Table(name="Notificacion")
public class NotificacionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idNotificacion")
    private Integer idNotificacion;
    @Basic(optional = false)
    @Column(name = "texto", nullable = false, length = 45)
    private String texto;
    @JoinColumn(name = "dueno", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private UsuarioEntity dueno;

    public NotificacionEntity() {
    }

    public NotificacionEntity(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public NotificacionEntity(Integer idNotificacion, String texto) {
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

    public UsuarioEntity getDueno() {
        return dueno;
    }

    public void setDueno(UsuarioEntity dueno) {
        this.dueno = dueno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNotificacion != null ? idNotificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotificacionEntity)) {
            return false;
        }
        NotificacionEntity other = (NotificacionEntity) object;
        if ((this.idNotificacion == null && other.idNotificacion != null) || (this.idNotificacion != null && !this.idNotificacion.equals(other.idNotificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectoTAW.entity.Notificacion[ idNotificacion=" + idNotificacion + " ]";
    }
    
    public NotificacionDTO toDTO(){
        NotificacionDTO n = new NotificacionDTO();
        n.setIdNotificacion(this.idNotificacion);
        n.setDueno(this.dueno.toDTO());
        n.setTexto(this.texto);
        return n;
    }

    public static List<NotificacionDTO> toDTOList(List<NotificacionEntity> lista){
        List<NotificacionDTO> result = new ArrayList<>();
        for(NotificacionEntity c:lista){
            result.add(c.toDTO());
        }

        return result;
    }
    
}
