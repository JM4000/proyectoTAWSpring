package es.proyectotawspring.dto;

public class UsuarioDTO {
    private static final long serialVersionUID = 1L;
    private Integer idUsuario;
    private String nombreUsuario;
    private String contrasena;
    private String nombre;
    private String apellidos;
    private String domicilio;
    private String ciudad;
    private Integer edad;
    private GeneroDTO genero;
    private TipousuarioDTO tipoUsuario;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    public UsuarioDTO(Integer idUsuario, String nombreUsuario, String contrasena, String nombre, String apellidos, String domicilio, String ciudad) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.domicilio = domicilio;
        this.ciudad = ciudad;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public GeneroDTO getGenero() {
        return genero;
    }

    public void setGenero(GeneroDTO genero) {
        this.genero = genero;
    }

    public TipousuarioDTO getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipousuarioDTO tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return nombreUsuario + ", " + nombre + " " + apellidos + ", " + ciudad + ", " + domicilio + ", " + edad + ", " + this.getGenero().getGenero() + ", " + this.getTipoUsuario().getTipoUsuario();
    }
}
