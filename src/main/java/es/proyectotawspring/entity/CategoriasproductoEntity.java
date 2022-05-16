package es.proyectotawspring.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "categoriasproducto", schema = "proyectotaw", catalog = "")
@IdClass(CategoriasproductoEntityPK.class)
public class CategoriasproductoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idProducto")
    private int idProducto;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idCategoria")
    private int idCategoria;

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoriasproductoEntity that = (CategoriasproductoEntity) o;
        return idProducto == that.idProducto && idCategoria == that.idCategoria;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, idCategoria);
    }
}
