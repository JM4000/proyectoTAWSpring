package es.proyectotawspring.entity;

import es.proyectotawspring.dto.TipousuarioDTO;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tipousuario", schema = "proyectotaw", catalog = "")
public class TipousuarioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "tipoUsuario")
    private String tipoUsuario;
    @OneToMany(mappedBy = "tipousuarioByTipoUsuario")
    private Collection<UsuarioEntity> usuariosByTipoUsuario;

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

        if (tipoUsuario != null ? !tipoUsuario.equals(that.tipoUsuario) : that.tipoUsuario != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return tipoUsuario != null ? tipoUsuario.hashCode() : 0;
    }

    public Collection<UsuarioEntity> getUsuariosByTipoUsuario() {
        return usuariosByTipoUsuario;
    }

    public void setUsuariosByTipoUsuario(Collection<UsuarioEntity> usuariosByTipoUsuario) {
        this.usuariosByTipoUsuario = usuariosByTipoUsuario;
    }

    public TipousuarioDTO toDTO() {
        TipousuarioDTO g = new TipousuarioDTO();

        g.setTipoUsuario(this.getTipoUsuario());

        return g;
    }
}
