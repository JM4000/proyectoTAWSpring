package es.proyectotawspring.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tipousuario", schema = "proyectotaw", catalog = "")
public class TipousuarioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "tipoUsuario")
    private String tipoUsuario;

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipousuarioEntity that = (TipousuarioEntity) o;
        return Objects.equals(tipoUsuario, that.tipoUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipoUsuario);
    }
}
