/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.proyectotawspring.entity;

import es.proyectotawspring.dto.TipousuarioDTO;
import es.proyectotawspring.dto.UsuarioDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juanm
 */
@Entity
@Table(name="Tipousuario")
public class TipousuarioEntity implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoUsuario")
    private List<UsuarioEntity> usuarioList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "tipoUsuario", nullable = false, length = 45)
    private String tipoUsuario;

    public TipousuarioEntity() {
    }

    public TipousuarioEntity(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoUsuario != null ? tipoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipousuarioEntity)) {
            return false;
        }
        TipousuarioEntity other = (TipousuarioEntity) object;
        if ((this.tipoUsuario == null && other.tipoUsuario != null) || (this.tipoUsuario != null && !this.tipoUsuario.equals(other.tipoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectoTAW.entity.Tipousuario[ tipoUsuario=" + tipoUsuario + " ]";
    }

    @XmlTransient
    public List<UsuarioEntity> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<UsuarioEntity> usuarioList) {
        this.usuarioList = usuarioList;
    }
    
    public TipousuarioDTO toDTO() {
        TipousuarioDTO tu = new TipousuarioDTO();
        tu.setTipoUsuario(this.getTipoUsuario());
        
        return tu;
    }

    public static List<TipousuarioDTO> toDTOList(List<TipousuarioEntity> lista){
        List<TipousuarioDTO> result = new ArrayList<>();
        for(TipousuarioEntity c:lista){
            result.add(c.toDTO());
        }

        return result;
    }
}
