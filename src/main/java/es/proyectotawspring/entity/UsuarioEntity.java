package es.proyectotawspring.entity;

import es.proyectotawspring.dto.GeneroDTO;
import es.proyectotawspring.dto.TipousuarioDTO;
import es.proyectotawspring.dto.UsuarioDTO;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "usuario", schema = "proyectotaw", catalog = "")
public class UsuarioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idUsuario")
    private int idUsuario;
    @Basic
    @Column(name = "nombreUsuario")
    private String nombreUsuario;
    @Basic
    @Column(name = "contrasena")
    private String contrasena;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "apellidos")
    private String apellidos;
    @Basic
    @Column(name = "domicilio")
    private String domicilio;
    @Basic
    @Column(name = "ciudad")
    private String ciudad;
    @Basic
    @Column(name = "edad")
    private Integer edad;
    @Basic
    @Column(name = "genero")
    private String genero;
    @Basic
    @Column(name = "tipoUsuario")
    private String tipoUsuario;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioEntity that = (UsuarioEntity) o;
        return idUsuario == that.idUsuario && Objects.equals(nombreUsuario, that.nombreUsuario) && Objects.equals(contrasena, that.contrasena) && Objects.equals(nombre, that.nombre) && Objects.equals(apellidos, that.apellidos) && Objects.equals(domicilio, that.domicilio) && Objects.equals(ciudad, that.ciudad) && Objects.equals(edad, that.edad) && Objects.equals(genero, that.genero) && Objects.equals(tipoUsuario, that.tipoUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, nombreUsuario, contrasena, nombre, apellidos, domicilio, ciudad, edad, genero, tipoUsuario);
    }

    public UsuarioDTO toDTO() {
        UsuarioDTO u = new UsuarioDTO();
        u.setApellidos(this.getApellidos());
        u.setCiudad(this.getCiudad());
        u.setContrasena(this.getContrasena());
        u.setDomicilio(this.getDomicilio());
        u.setEdad(this.getEdad());
        u.setGenero(new GeneroDTO(this.getGenero()));
        u.setIdUsuario(this.getIdUsuario());
        u.setNombre(this.getNombre());
        u.setNombreUsuario(this.getNombreUsuario());
        u.setTipoUsuario(new TipousuarioDTO(this.getTipoUsuario()));

        return u;
    }
}
