package es.proyectotawspring.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "categoriasfavoritas", schema = "proyectotaw", catalog = "")
@IdClass(CategoriasfavoritasEntityPK.class)
public class CategoriasfavoritasEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idUsuario")
    private int idUsuario;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idCategoria")
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
        CategoriasfavoritasEntity that = (CategoriasfavoritasEntity) o;
        return idUsuario == that.idUsuario && idCategoria == that.idCategoria;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, idCategoria);
    }
}
