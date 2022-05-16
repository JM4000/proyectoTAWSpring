package es.proyectotawspring.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CategoriasfavoritasEntityPK implements Serializable {
    @Column(name = "idUsuario")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;
    @Column(name = "idCategoria")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategoria;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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
        CategoriasfavoritasEntityPK that = (CategoriasfavoritasEntityPK) o;
        return idUsuario == that.idUsuario && idCategoria == that.idCategoria;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, idCategoria);
    }
}
