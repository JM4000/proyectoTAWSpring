/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.proyectotawspring.entity;

import es.proyectotawspring.dto.ProductoDTO;

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
@Table(name="Producto")
public class ProductoEntity implements Serializable {

    @JoinColumn(name = "idComprador", referencedColumnName = "idUsuario")
    @ManyToOne
    private UsuarioEntity idComprador;
    

    @JoinTable(name = "productos_favoritos", joinColumns = {
        @JoinColumn(name = "Producto_idProducto", referencedColumnName = "idProducto")}, inverseJoinColumns = {
        @JoinColumn(name = "Usuario_idUsuario", referencedColumnName = "idUsuario")})
    @ManyToMany
    private List<UsuarioEntity> usuarioList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProducto")
    private Integer idProducto;
    @Basic(optional = false)
    @Column(name = "titulo", nullable = false, length = 45)
    private String titulo;
    @Basic(optional = false)
    @Column(name = "descripcion", nullable = false, length = 1000)
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "foto", nullable = false, length = 1000)
    private String foto;
    @Basic(optional = false)
    @Column(name = "precioSalida", nullable = false)
    private double precioSalida;
    @ManyToMany(mappedBy = "productoList")
    private List<CategoriaEntity> categoriaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    private List<SubastaEntity> subastaList;

    public ProductoEntity() {
    }

    public ProductoEntity(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public ProductoEntity(Integer idProducto, String titulo, String descripcion, String foto, double precioSalida) {
        this.idProducto = idProducto;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.foto = foto;
        this.precioSalida = precioSalida;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public double getPrecioSalida() {
        return precioSalida;
    }

    public void setPrecioSalida(double precioSalida) {
        this.precioSalida = precioSalida;
    }

    @XmlTransient
    public List<CategoriaEntity> getCategoriaList() {
        return categoriaList;
    }

    public void setCategoriaList(List<CategoriaEntity> categoriaList) {
        this.categoriaList = categoriaList;
    }

    @XmlTransient
    public List<SubastaEntity> getSubastaList() {
        return subastaList;
    }

    public void setSubastaList(List<SubastaEntity> subastaList) {
        this.subastaList = subastaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoEntity)) {
            return false;
        }
        ProductoEntity other = (ProductoEntity) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectoTAW.entity.Producto[ idProducto=" + idProducto + " ]";
    }

    @XmlTransient
    public List<UsuarioEntity> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<UsuarioEntity> usuarioList) {
        this.usuarioList = usuarioList;
    }
    
    public ProductoDTO toDTO() {
        ProductoDTO pr = new ProductoDTO();
        pr.setDescripcion(this.getDescripcion());
        pr.setFoto(this.getFoto());
        pr.setIdProducto(this.getIdProducto());
        pr.setPrecioSalida(this.getPrecioSalida());
        pr.setTitulo(this.getTitulo());
        pr.setCategoriaList(CategoriaEntity.toDTOList(this.getCategoriaList()));
        if (this.getIdComprador() != null){
            pr.setComprador(this.getIdComprador().toDTO());
        }
        
        
        return pr;
    }

    public static List<ProductoDTO> toDTOList(List<ProductoEntity> lista){
        List<ProductoDTO> result = new ArrayList<>();
        for(ProductoEntity c:lista){
            result.add(c.toDTO());
        }

        return result;
    }

    public UsuarioEntity getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(UsuarioEntity idComprador) {
        this.idComprador = idComprador;
    }
}
