package es.proyectotawspring.Util;

public class BusquedaParaLista {
    private Integer filtro;
    private String busqueda;
    private Integer idlist;

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

    public Integer getIdlist() {
        return idlist;
    }

    public void setIdlist(Integer idlist) {
        this.idlist = idlist;
    }

    public String toString(){
        return filtro + " " + busqueda + " " + idlist;
    }
}
