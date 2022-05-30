package es.proyectotawspring.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "lista", schema = "proyectotaw", catalog = "")
public class ListaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idlista")
    private int idlista;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "categoria_idCategoria", referencedColumnName = "idCategoria", nullable = false)
    private CategoriaEntity categoriaByCategoriaIdCategoria;
    @OneToMany(mappedBy = "listaByIdlista")
    private Collection<ListausuarioEntity> listausuariosByIdlista;

    public int getIdlista() {
        return idlista;
    }

    public void setIdlista(int idlista) {
        this.idlista = idlista;
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

        if (idlista != that.idlista) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idlista;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }

    public CategoriaEntity getCategoriaByCategoriaIdCategoria() {
        return categoriaByCategoriaIdCategoria;
    }

    public void setCategoriaByCategoriaIdCategoria(CategoriaEntity categoriaByCategoriaIdCategoria) {
        this.categoriaByCategoriaIdCategoria = categoriaByCategoriaIdCategoria;
    }

    public Collection<ListausuarioEntity> getListausuariosByIdlista() {
        return listausuariosByIdlista;
    }

    public void setListausuariosByIdlista(Collection<ListausuarioEntity> listausuariosByIdlista) {
        this.listausuariosByIdlista = listausuariosByIdlista;
    }
}
