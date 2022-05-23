package es.proyectotawspring.dto;

public class TipousuarioDTO {private static final long serialVersionUID = 1L;
    private String tipoUsuario;

    public TipousuarioDTO() {
    }

    public TipousuarioDTO(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}
