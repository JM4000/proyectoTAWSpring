package es.proyectotawspring.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "listausuario", schema = "proyectotaw", catalog = "")
@IdClass(ListausuarioEntityPK.class)
public class ListausuarioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idlista")
    private int idlista;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idUsuario")
    private int idUsuario;

    public int getIdlista() {
        return idlista;
    }

    public void setIdlista(int idlista) {
        this.idlista = idlista;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListausuarioEntity that = (ListausuarioEntity) o;
        return idlista == that.idlista && idUsuario == that.idUsuario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idlista, idUsuario);
    }
}
