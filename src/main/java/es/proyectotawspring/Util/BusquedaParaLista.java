package es.proyectotawspring.Util;

public class BusquedaParaLista {
    private Integer filtro;
    private String busqueda;
    private Integer idlista;

    public Integer getFiltro() {
        return filtro;
    }

    public void setFiltro(Integer filtro) {
        this.filtro = filtro;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public Integer getIdlista() {
        return idlista;
    }

    public void setIdlista(Integer idlista) {
        this.idlista = idlista;
    }

    public String toString(){
        return filtro + " " + busqueda + " " + idlista;
    }
}
