package es.proyectotawspring.Util;

public class Busqueda {
    private Integer filtro;
    private String busqueda;

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

    public String toString(){
        return filtro + " " + busqueda;
    }
}
