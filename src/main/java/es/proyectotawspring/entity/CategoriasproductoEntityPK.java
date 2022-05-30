package es.proyectotawspring.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class CategoriasproductoEntityPK implements Serializable {
    @Column(name = "idProducto")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProducto;
    @Column(name = "idCategoria")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

        CategoriasproductoEntityPK that = (CategoriasproductoEntityPK) o;

        if (idProducto != that.idProducto) return false;
        if (idCategoria != that.idCategoria) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idProducto;
        result = 31 * result + idCategoria;
        return result;
    }
}
