/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.proyectotawspring.entity;

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
@Table(name="Usuario")
public class UsuarioEntity implements Serializable {

    @ManyToMany(mappedBy = "usuarioList")
    private List<ListaEntity> listaList;

    @ManyToMany(mappedBy = "usuarioList")
    private List<CategoriaEntity> categoriaList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUsuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @Column(name = "nombreUsuario", nullable = false, length = 45)
    private String nombreUsuario;
    @Basic(optional = false)
    @Column(name = "contrasena", nullable = false, length = 45)
    private String contrasena;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellidos", nullable = false, length = 45)
    private String apellidos;
    @Basic(optional = false)
    @Column(name = "domicilio", nullable = false, length = 45)
    private String domicilio;
    @Basic(optional = false)
    @Column(name = "ciudad", nullable = false, length = 45)
    private String ciudad;
    @Column(name = "edad")
    private Integer edad;
    @ManyToMany(mappedBy = "usuarioList")
    private List<ProductoEntity> productoList;
    @JoinColumn(name = "genero", referencedColumnName = "Genero")
    @ManyToOne(optional = false)
    private GeneroEntity genero;
    @JoinColumn(name = "tipoUsuario", referencedColumnName = "tipoUsuario")
    @ManyToOne(optional = false)
    private TipousuarioEntity tipoUsuario;

    public UsuarioEntity() {
    }

    public UsuarioEntity(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public UsuarioEntity(Integer idUsuario, String nombreUsuario, String contrasena, String nombre, String apellidos, String domicilio, String ciudad) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.domicilio = domicilio;
        this.ciudad = ciudad;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @XmlTransient
    public List<ProductoEntity> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<ProductoEntity> productoList) {
        this.productoList = productoList;
    }

    public GeneroEntity getGenero() {
        return genero;
    }

    public void setGenero(GeneroEntity genero) {
        this.genero = genero;
    }

    public TipousuarioEntity getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipousuarioEntity tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
       

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioEntity)) {
            return false;
        }
        UsuarioEntity other = (UsuarioEntity) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreUsuario + ", " + nombre + " " + apellidos + ", " + ciudad + ", " + domicilio + ", " + edad + ", " + getGenero().getGenero() + ", " + getTipoUsuario().getTipoUsuario() + ", ";
    }

    @XmlTransient
    public List<CategoriaEntity> getCategoriaList() {
        return categoriaList;
    }

    public void setCategoriaList(List<CategoriaEntity> categoriaList) {
        this.categoriaList = categoriaList;
    }

    public UsuarioDTO toDTO() {
        
        UsuarioDTO u = new UsuarioDTO();
        u.setApellidos(this.getApellidos());
        u.setCiudad(this.getCiudad());
        u.setContrasena(this.getContrasena());
        u.setDomicilio(this.getDomicilio());
        u.setEdad(this.getEdad());
        u.setGenero(this.getGenero().toDTO());
        u.setIdUsuario(this.getIdUsuario());
        u.setNombre(this.getNombre());
        u.setNombreUsuario(this.getNombreUsuario());
        u.setTipoUsuario(this.getTipoUsuario().toDTO());
            
        return u;
    }
    
    public static List<UsuarioDTO> toDTOList(List<UsuarioEntity> lista){
        List<UsuarioDTO> result = new ArrayList<>();
        for(UsuarioEntity c:lista){
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
