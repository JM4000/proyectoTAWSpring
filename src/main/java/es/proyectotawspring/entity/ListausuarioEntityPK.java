package es.proyectotawspring.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class ListausuarioEntityPK implements Serializable {
    @Column(name = "idlista")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idlista;
    @Column(name = "idUsuario")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

        ListausuarioEntityPK that = (ListausuarioEntityPK) o;

        if (idlista != that.idlista) return false;
        if (idUsuario != that.idUsuario) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idlista;
        result = 31 * result + idUsuario;
        return result;
    }
}