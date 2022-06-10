/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.proyectotawspring.entity;

import es.proyectotawspring.dto.CategoriaDTO;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juanm
 */
@Entity
@Table(name="Categoria")
public class CategoriaEntity implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoriaidCategoria")
    private List<ListaEntity> listaList;
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCategoria")
    private Integer idCategoria;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;
    @JoinTable(name = "categoriasproducto", joinColumns = {
        @JoinColumn(name = "idCategoria", referencedColumnName = "idCategoria")}, inverseJoinColumns = {
        @JoinColumn(name = "idProducto", referencedColumnName = "idProducto")})
    @ManyToMany
    private List<ProductoEntity> productoList;
    @JoinTable(name = "categoriasfavoritas", joinColumns = {
        @JoinColumn(name = "idCategoria", referencedColumnName = "idCategoria")}, inverseJoinColumns = {
        @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")})
    @ManyToMany
    private List<UsuarioEntity> usuarioList;

    public CategoriaEntity() {
    }

    public CategoriaEntity(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public CategoriaEntity(Integer idCategoria, String nombre) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<ProductoEntity> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<ProductoEntity> productoList) {
        this.productoList = productoList;
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
        hash += (idCategoria != null ? idCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriaEntity)) {
            return false;
        }
        CategoriaEntity other = (CategoriaEntity) object;
        if ((this.idCategoria == null && other.idCategoria != null) || (this.idCategoria != null && !this.idCategoria.equals(other.idCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectoTAW.entity.Categoria[ idCategoria=" + idCategoria + " ]";
    }
   
    public CategoriaDTO toDTO() {
        
        CategoriaDTO cat = new CategoriaDTO();
        cat.setIdCategoria(this.getIdCategoria());
        cat.setNombre(this.getNombre());
        
        return cat;
    }
    
    public static List<CategoriaDTO> toDTOList(List<CategoriaEntity> lista){
        
        List<CategoriaDTO> result = new ArrayList<>();
        for(CategoriaEntity c:lista){
            result.add(c.toDTO());
        }
        
        return result;
    }




    @XmlTransient
    public List<ListaEntity> getListaList() {
        return listaList;
    }

    public void setListaList(List<ListaEntity> listaList) {
        this.listaList = listaList;
    }
}
