package es.proyectotawspring.entity;

import javax.persistence.*;
import java.util.Objects;

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
    @Basic
    @Column(name = "dueno")
    private int dueno;

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

    public int getDueno() {
        return dueno;
    }

    public void setDueno(int dueno) {
        this.dueno = dueno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificacionEntity that = (NotificacionEntity) o;
        return idNotificacion == that.idNotificacion && dueno == that.dueno && Objects.equals(texto, that.texto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNotificacion, texto, dueno);
    }
}
