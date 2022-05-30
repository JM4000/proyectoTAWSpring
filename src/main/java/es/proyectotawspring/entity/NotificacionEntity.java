package es.proyectotawspring.entity;

import javax.persistence.*;

@Entity
@Table(name = "notificacion", schema = "proyectotaw", catalog = "")
public class NotificacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idNotificacion")
    private int idNotificacion;
    @Basic
    @Column(name = "texto")
    private String texto;
    @ManyToOne
    @JoinColumn(name = "dueno", referencedColumnName = "idUsuario", nullable = false)
    private UsuarioEntity usuarioByDueno;

    public int getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(int idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotificacionEntity that = (NotificacionEntity) o;

        if (idNotificacion != that.idNotificacion) return false;
        if (texto != null ? !texto.equals(that.texto) : that.texto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idNotificacion;
        result = 31 * result + (texto != null ? texto.hashCode() : 0);
        return result;
    }

    public UsuarioEntity getUsuarioByDueno() {
        return usuarioByDueno;
    }

    public void setUsuarioByDueno(UsuarioEntity usuarioByDueno) {
        this.usuarioByDueno = usuarioByDueno;
    }
}
