package es.proyectotawspring.entity;

import javax.persistence.*;

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
    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", nullable = false)
    private UsuarioEntity usuarioByIdUsuario;
    @ManyToOne
    @JoinColumn(name = "idCategoria", referencedColumnName = "idCategoria", nullable = false)
    private CategoriaEntity categoriaByIdCategoria;

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

        if (idUsuario != that.idUsuario) return false;
        if (idCategoria != that.idCategoria) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUsuario;
        result = 31 * result + idCategoria;
        return result;
    }

    public UsuarioEntity getUsuarioByIdUsuario() {
        return usuarioByIdUsuario;
    }

    public void setUsuarioByIdUsuario(UsuarioEntity usuarioByIdUsuario) {
        this.usuarioByIdUsuario = usuarioByIdUsuario;
    }

    public CategoriaEntity getCategoriaByIdCategoria() {
        return categoriaByIdCategoria;
    }

    public void setCategoriaByIdCategoria(CategoriaEntity categoriaByIdCategoria) {
        this.categoriaByIdCategoria = categoriaByIdCategoria;
    }
}
