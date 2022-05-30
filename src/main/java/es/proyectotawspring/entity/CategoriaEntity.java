package es.proyectotawspring.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "categoria", schema = "proyectotaw", catalog = "")
public class CategoriaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idCategoria")
    private int idCategoria;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "categoriaByIdCategoria")
    private Collection<CategoriasfavoritasEntity> categoriasfavoritasByIdCategoria;
    @OneToMany(mappedBy = "categoriaByIdCategoria")
    private Collection<CategoriasproductoEntity> categoriasproductosByIdCategoria;
    @OneToMany(mappedBy = "categoriaByCategoriaIdCategoria")
    private Collection<ListaEntity> listasByIdCategoria;

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoriaEntity that = (CategoriaEntity) o;

        if (idCategoria != that.idCategoria) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCategoria;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }

    public Collection<CategoriasfavoritasEntity> getCategoriasfavoritasByIdCategoria() {
        return categoriasfavoritasByIdCategoria;
    }

    public void setCategoriasfavoritasByIdCategoria(Collection<CategoriasfavoritasEntity> categoriasfavoritasByIdCategoria) {
        this.categoriasfavoritasByIdCategoria = categoriasfavoritasByIdCategoria;
    }

    public Collection<CategoriasproductoEntity> getCategoriasproductosByIdCategoria() {
        return categoriasproductosByIdCategoria;
    }

    public void setCategoriasproductosByIdCategoria(Collection<CategoriasproductoEntity> categoriasproductosByIdCategoria) {
        this.categoriasproductosByIdCategoria = categoriasproductosByIdCategoria;
    }

    public Collection<ListaEntity> getListasByIdCategoria() {
        return listasByIdCategoria;
    }

    public void setListasByIdCategoria(Collection<ListaEntity> listasByIdCategoria) {
        this.listasByIdCategoria = listasByIdCategoria;
    }
}
