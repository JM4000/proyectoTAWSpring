package es.proyectotawspring.Util;

public class ObjetoPuja {
    private String cantidad;
    private Integer idSubasta;
    private Integer idMayorPostor;


    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getIdSubasta() {
        return idSubasta;
    }

    public void setIdSubasta(Integer idSubasta) {
        this.idSubasta = idSubasta;
    }

    public Integer getIdMayorPostor() {
        return idMayorPostor;
    }

    public void setIdMayorPostor(Integer idMayorPostor) {
        this.idMayorPostor = idMayorPostor;
    }
}
