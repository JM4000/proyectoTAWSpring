/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.proyectotawspring.entity;

import es.proyectotawspring.dto.GeneroDTO;
import es.proyectotawspring.dto.ListaDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juanm
 */
@Entity@Table(name="Genero")
public class GeneroEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Genero", nullable = false, length = 45)
    private String genero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genero")
    private List<UsuarioEntity> usuarioList;

    public GeneroEntity() {
    }

    public GeneroEntity(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @XmlTransient
    public List<UsuarioEntity> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<UsuarioEntity> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (genero != null ? genero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GeneroEntity)) {
            return false;
        }
        GeneroEntity other = (GeneroEntity) object;
        if ((this.genero == null && other.genero != null) || (this.genero != null && !this.genero.equals(other.genero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectoTAW.entity.Genero[ genero=" + genero + " ]";
    }

    public GeneroDTO toDTO() {
        GeneroDTO g = new GeneroDTO();

        g.setGenero(this.getGenero());
        
        return g;
    }

    public static List<GeneroDTO> toDTOList(List<GeneroEntity> lista){
        List<GeneroDTO> result = new ArrayList<>();
        for(GeneroEntity c:lista){
            result.add(c.toDTO());
        }

        return result;
    }
}
