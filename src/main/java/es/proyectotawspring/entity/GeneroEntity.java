package es.proyectotawspring.entity;

import es.proyectotawspring.dto.GeneroDTO;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "genero", schema = "proyectotaw", catalog = "")
public class GeneroEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Genero")
    private String genero;
    @OneToMany(mappedBy = "generoByGenero")
    private Collection<UsuarioEntity> usuariosByGenero;

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GeneroEntity that = (GeneroEntity) o;

        if (genero != null ? !genero.equals(that.genero) : that.genero != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return genero != null ? genero.hashCode() : 0;
    }

    public Collection<UsuarioEntity> getUsuariosByGenero() {
        return usuariosByGenero;
    }

    public void setUsuariosByGenero(Collection<UsuarioEntity> usuariosByGenero) {
        this.usuariosByGenero = usuariosByGenero;
    }

    public GeneroDTO toDTO() {
        GeneroDTO g = new GeneroDTO();

        g.setGenero(this.getGenero());

        return g;
    }
}
