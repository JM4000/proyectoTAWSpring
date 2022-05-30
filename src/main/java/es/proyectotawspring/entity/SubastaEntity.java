/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.proyectotawspring.entity;

import es.proyectotawspring.dto.SubastaDTO;
import es.proyectotawspring.dto.TipousuarioDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author juanm
 */
@Entity
@Table(name="Subasta")
public class SubastaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSubasta")
    private Integer idSubasta;
    @Basic(optional = false)
    @Column(name = "predioActual", nullable = false)
    private double predioActual;
    @Basic(optional = false)
    @Column(name = "fechaCierre", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaCierre;
    @JoinColumn(name = "producto", referencedColumnName = "idProducto")
    @ManyToOne(optional = false)
    private ProductoEntity producto;
    @JoinColumn(name = "mayorPostor", referencedColumnName = "idUsuario")
    @ManyToOne
    private UsuarioEntity mayorPostor;
    @JoinColumn(name = "creador", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private UsuarioEntity creador;

    public SubastaEntity() {
    }

    public SubastaEntity(Integer idSubasta) {
        this.idSubasta = idSubasta;
    }

    public SubastaEntity(Integer idSubasta, double predioActual, Date fechaCierre) {
        this.idSubasta = idSubasta;
        this.predioActual = predioActual;
        this.fechaCierre = fechaCierre;
    }

    public Integer getIdSubasta() {
        return idSubasta;
    }

    public void setIdSubasta(Integer idSubasta) {
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

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public UsuarioEntity getMayorPostor() {
        return mayorPostor;
    }

    public void setMayorPostor(UsuarioEntity mayorPostor) {
        this.mayorPostor = mayorPostor;
    }

    public UsuarioEntity getCreador() {
        return creador;
    }

    public void setCreador(UsuarioEntity creador) {
        this.creador = creador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSubasta != null ? idSubasta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubastaEntity)) {
            return false;
        }
        SubastaEntity other = (SubastaEntity) object;
        if ((this.idSubasta == null && other.idSubasta != null) || (this.idSubasta != null && !this.idSubasta.equals(other.idSubasta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectoTAW.entity.Subasta[ idSubasta=" + idSubasta + " ]";
    }

    public SubastaDTO toDTO() {
        SubastaDTO s = new SubastaDTO();
        s.setCreador(this.getCreador().toDTO());
        s.setFechaCierre(this.getFechaCierre());
        s.setIdSubasta(this.getIdSubasta());
        if (this.getMayorPostor()!= null){
             s.setMayorPostor(this.getMayorPostor().toDTO());
        }
       
        s.setPredioActual(this.getPredioActual());
        s.setProducto(this.getProducto().toDTO());

        return s;
    }

    public static List<SubastaDTO> toDTOList(List<SubastaEntity> lista){
        List<SubastaDTO> result = new ArrayList<>();
        for(SubastaEntity c:lista){
            result.add(c.toDTO());
        }

        return result;
    }
}
