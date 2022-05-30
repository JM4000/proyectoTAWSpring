/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.proyectotawspring.dto;

import java.util.Date;

/**
 *
 * @author juanm
 */
public class SubastaDTO {
    
    
    private static final long serialVersionUID = 1L;
    private Integer idSubasta;
    private double predioActual;
    private Date fechaCierre;
    private ProductoDTO producto;
    private UsuarioDTO mayorPostor;
    private UsuarioDTO creador;

    public SubastaDTO() {
    }

    public SubastaDTO(Integer idSubasta) {
        this.idSubasta = idSubasta;
    }

    public SubastaDTO(Integer idSubasta, double predioActual, Date fechaCierre) {
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

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public UsuarioDTO getMayorPostor() {
        return mayorPostor;
    }

    public void setMayorPostor(UsuarioDTO mayorPostor) {
        this.mayorPostor = mayorPostor;
    }

    public UsuarioDTO getCreador() {
        return creador;
    }

    public void setCreador(UsuarioDTO creador) {
        this.creador = creador;
    }
}
