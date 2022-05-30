/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.proyectotawspring.entity;

import es.proyectotawspring.dto.ListaDTO;
import es.proyectotawspring.dto.NotificacionDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Agustín
 */
@Entity
@Table(name="Lista")
public class ListaEntity implements Serializable {

    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlista")
    private Integer idlista;
    @JoinTable(name = "listausuario", joinColumns = {
        @JoinColumn(name = "idlista", referencedColumnName = "idlista")}, inverseJoinColumns = {
        @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")})
    @ManyToMany
    private List<UsuarioEntity> usuarioList;
    @JoinColumn(name = "categoria_idCategoria", referencedColumnName = "idCategoria")
    @ManyToOne(optional = false)
    private CategoriaEntity categoriaidCategoria;

    public ListaEntity() {
    }

    public ListaEntity(Integer idlista, String nombre) {
        this.idlista = idlista;
        this.nombre = nombre;
    }

    public Integer getIdlista() {
        return idlista;
    }

    public void setIdlista(Integer idlista) {
        this.idlista = idlista;
    }

    @XmlTransient
    public List<UsuarioEntity> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<UsuarioEntity> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public CategoriaEntity getCategoriaidCategoria() {
        return categoriaidCategoria;
    }

    public void setCategoriaidCategoria(CategoriaEntity categoriaidCategoria) {
        this.categoriaidCategoria = categoriaidCategoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlista != null ? idlista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListaEntity)) {
            return false;
        }
        ListaEntity other = (ListaEntity) object;
        if ((this.idlista == null && other.idlista != null) || (this.idlista != null && !this.idlista.equals(other.idlista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectoTAW.entity.Lista[ idlista=" + idlista + " ]";
    }
    
    public ListaDTO toDTO(){
        ListaDTO ld = new ListaDTO();
        ld.setidlista(this.getIdlista());
        ld.setusuarioList(UsuarioEntity.toDTOList(this.getUsuarioList()));
        ld.setcategoriaidCategoria(this.getCategoriaidCategoria().toDTO());
        ld.setnombre(this.getNombre());
        return ld;
    }

    public static List<ListaDTO> toDTOList(List<ListaEntity> lista){
        List<ListaDTO> result = new ArrayList<>();
        for(ListaEntity c:lista){
            result.add(c.toDTO());
        }

        return result;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
