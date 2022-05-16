package es.proyectotawspring.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

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
    @Basic
    @Column(name = "mayorPostor")
    private Integer mayorPostor;
    @Basic
    @Column(name = "creador")
    private int creador;
    @Basic
    @Column(name = "producto")
    private int producto;

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

    public Integer getMayorPostor() {
        return mayorPostor;
    }

    public void setMayorPostor(Integer mayorPostor) {
        this.mayorPostor = mayorPostor;
    }

    public int getCreador() {
        return creador;
    }

    public void setCreador(int creador) {
        this.creador = creador;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubastaEntity that = (SubastaEntity) o;
        return idSubasta == that.idSubasta && Double.compare(that.predioActual, predioActual) == 0 && creador == that.creador && producto == that.producto && Objects.equals(fechaCierre, that.fechaCierre) && Objects.equals(mayorPostor, that.mayorPostor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSubasta, predioActual, fechaCierre, mayorPostor, creador, producto);
    }
}
