package es.proyectotawspring.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "lista", schema = "proyectotaw", catalog = "")
public class ListaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idlista")
    private int idlista;
    @Basic
    @Column(name = "categoria_idCategoria")
    private int categoriaIdCategoria;
    @Basic
    @Column(name = "nombre")
    private String nombre;

    public int getIdlista() {
        return idlista;
    }

    public void setIdlista(int idlista) {
        this.idlista = idlista;
    }

    public int getCategoriaIdCategoria() {
        return categoriaIdCategoria;
    }

    public void setCategoriaIdCategoria(int categoriaIdCategoria) {
        this.categoriaIdCategoria = categoriaIdCategoria;
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
        ListaEntity that = (ListaEntity) o;
        return idlista == that.idlista && categoriaIdCategoria == that.categoriaIdCategoria && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idlista, categoriaIdCategoria, nombre);
    }
}
