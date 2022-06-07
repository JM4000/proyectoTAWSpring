package es.proyectotawspring.Util;

public class FiltroPaginaPrincipal {
    private String filtro;
    private String categoriaNombre;

    private String busqueda;

    public FiltroPaginaPrincipal(){}

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }



    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }
}
