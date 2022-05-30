package es.proyectotawspring.entity;

import javax.persistence.*;

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
    @ManyToOne
    @JoinColumn(name = "idlista", referencedColumnName = "idlista", nullable = false)
    private ListaEntity listaByIdlista;
    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", nullable = false)
    private UsuarioEntity usuarioByIdUsuario;

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

    public ListaEntity getListaByIdlista() {
        return listaByIdlista;
    }

    public void setListaByIdlista(ListaEntity listaByIdlista) {
        this.listaByIdlista = listaByIdlista;
    }

    public UsuarioEntity getUsuarioByIdUsuario() {
        return usuarioByIdUsuario;
    }

    public void setUsuarioByIdUsuario(UsuarioEntity usuarioByIdUsuario) {
        this.usuarioByIdUsuario = usuarioByIdUsuario;
    }
}
