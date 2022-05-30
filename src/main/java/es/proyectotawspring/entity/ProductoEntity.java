package es.proyectotawspring.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "producto", schema = "proyectotaw", catalog = "")
public class ProductoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idProducto")
    private int idProducto;
    @Basic
    @Column(name = "titulo")
    private String titulo;
    @Basic
    @Column(name = "descripcion")
    private String descripcion;
    @Basic
    @Column(name = "foto")
    private String foto;
    @Basic
    @Column(name = "precioSalida")
    private double precioSalida;
    @OneToMany(mappedBy = "productoByIdProducto")
    private Collection<CategoriasproductoEntity> categoriasproductosByIdProducto;
    @ManyToOne
    @JoinColumn(name = "idComprador", referencedColumnName = "idUsuario")
    private UsuarioEntity usuarioByIdComprador;
    @OneToMany(mappedBy = "productoByProductoIdProducto")
    private Collection<ProductosFavoritosEntity> productosFavoritosByIdProducto;
    @OneToMany(mappedBy = "productoByProducto")
    private Collection<SubastaEntity> subastasByIdProducto;

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductoEntity that = (ProductoEntity) o;

        if (idProducto != that.idProducto) return false;
        if (Double.compare(that.precioSalida, precioSalida) != 0) return false;
        if (titulo != null ? !titulo.equals(that.titulo) : that.titulo != null) return false;
        if (descripcion != null ? !descripcion.equals(that.descripcion) : that.descripcion != null) return false;
        if (foto != null ? !foto.equals(that.foto) : that.foto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idProducto;
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + (foto != null ? foto.hashCode() : 0);
        temp = Double.doubleToLongBits(precioSalida);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public Collection<CategoriasproductoEntity> getCategoriasproductosByIdProducto() {
        return categoriasproductosByIdProducto;
    }

    public void setCategoriasproductosByIdProducto(Collection<CategoriasproductoEntity> categoriasproductosByIdProducto) {
        this.categoriasproductosByIdProducto = categoriasproductosByIdProducto;
    }

    public UsuarioEntity getUsuarioByIdComprador() {
        return usuarioByIdComprador;
    }

    public void setUsuarioByIdComprador(UsuarioEntity usuarioByIdComprador) {
        this.usuarioByIdComprador = usuarioByIdComprador;
    }

    public Collection<ProductosFavoritosEntity> getProductosFavoritosByIdProducto() {
        return productosFavoritosByIdProducto;
    }

    public void setProductosFavoritosByIdProducto(Collection<ProductosFavoritosEntity> productosFavoritosByIdProducto) {
        this.productosFavoritosByIdProducto = productosFavoritosByIdProducto;
    }

    public Collection<SubastaEntity> getSubastasByIdProducto() {
        return subastasByIdProducto;
    }

    public void setSubastasByIdProducto(Collection<SubastaEntity> subastasByIdProducto) {
        this.subastasByIdProducto = subastasByIdProducto;
    }
}
