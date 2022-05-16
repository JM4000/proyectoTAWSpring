package es.proyectotawspring.entity;

import javax.persistence.*;
import java.util.Objects;

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
    @Basic
    @Column(name = "idComprador")
    private Integer idComprador;

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

    public Integer getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(Integer idComprador) {
        this.idComprador = idComprador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductoEntity that = (ProductoEntity) o;
        return idProducto == that.idProducto && Double.compare(that.precioSalida, precioSalida) == 0 && Objects.equals(titulo, that.titulo) && Objects.equals(descripcion, that.descripcion) && Objects.equals(foto, that.foto) && Objects.equals(idComprador, that.idComprador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, titulo, descripcion, foto, precioSalida, idComprador);
    }
}
