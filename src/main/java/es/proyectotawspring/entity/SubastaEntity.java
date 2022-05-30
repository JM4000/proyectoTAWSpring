package es.proyectotawspring.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "subasta", schema = "proyectotaw", catalog = "")
public class SubastaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idSubasta")
    private int idSubasta;
    @Basic
    @Column(name = "predioActual")
    private double predioActual;
    @Basic
    @Column(name = "fechaCierre")
    private Date fechaCierre;
    @ManyToOne
    @JoinColumn(name = "mayorPostor", referencedColumnName = "idUsuario")
    private UsuarioEntity usuarioByMayorPostor;
    @ManyToOne
    @JoinColumn(name = "creador", referencedColumnName = "idUsuario", nullable = false)
    private UsuarioEntity usuarioByCreador;
    @ManyToOne
    @JoinColumn(name = "producto", referencedColumnName = "idProducto", nullable = false)
    private ProductoEntity productoByProducto;

    public int getIdSubasta() {
        return idSubasta;
    }

    public void setIdSubasta(int idSubasta) {
        this.idSubasta = idSubasta;
    }

    public double getPredioActual() {
        return predioActual;
    }

    public void setPredioActual(double predioActual) {
        this.predioActual = predioActual;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubastaEntity that = (SubastaEntity) o;

        if (idSubasta != that.idSubasta) return false;
        if (Double.compare(that.predioActual, predioActual) != 0) return false;
        if (fechaCierre != null ? !fechaCierre.equals(that.fechaCierre) : that.fechaCierre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idSubasta;
        temp = Double.doubleToLongBits(predioActual);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (fechaCierre != null ? fechaCierre.hashCode() : 0);
        return result;
    }

    public UsuarioEntity getUsuarioByMayorPostor() {
        return usuarioByMayorPostor;
    }

    public void setUsuarioByMayorPostor(UsuarioEntity usuarioByMayorPostor) {
        this.usuarioByMayorPostor = usuarioByMayorPostor;
    }

    public UsuarioEntity getUsuarioByCreador() {
        return usuarioByCreador;
    }

    public void setUsuarioByCreador(UsuarioEntity usuarioByCreador) {
        this.usuarioByCreador = usuarioByCreador;
    }

    public ProductoEntity getProductoByProducto() {
        return productoByProducto;
    }

    public void setProductoByProducto(ProductoEntity productoByProducto) {
        this.productoByProducto = productoByProducto;
    }
}
