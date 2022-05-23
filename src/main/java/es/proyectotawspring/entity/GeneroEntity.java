package es.proyectotawspring.entity;

import es.proyectotawspring.dto.GeneroDTO;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "genero", schema = "proyectotaw", catalog = "")
public class GeneroEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Genero")
    private String genero;

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
        return Objects.equals(genero, that.genero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genero);
    }

    public GeneroDTO toDTO() {
        GeneroDTO g = new GeneroDTO();

        g.setGenero(this.getGenero());

        return g;
    }
}
