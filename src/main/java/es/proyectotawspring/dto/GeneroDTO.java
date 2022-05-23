package es.proyectotawspring.dto;

public class GeneroDTO {
    private static final long serialVersionUID = 1L;
    private String genero;

    public GeneroDTO() {
    }

    public GeneroDTO(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public GeneroDTO toDTO() {
        GeneroDTO g = new GeneroDTO();

        g.setGenero(this.getGenero());

        return g;
    }
}
