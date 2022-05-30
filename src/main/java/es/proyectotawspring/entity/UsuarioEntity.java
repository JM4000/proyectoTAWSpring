package es.proyectotawspring.entity;

import es.proyectotawspring.dto.GeneroDTO;
import es.proyectotawspring.dto.TipousuarioDTO;
import es.proyectotawspring.dto.UsuarioDTO;

import javax.persistence.*;
import java.util.Collection;

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
    @OneToMany(mappedBy = "usuarioByIdUsuario")
    private Collection<CategoriasfavoritasEntity> categoriasfavoritasByIdUsuario;
    @OneToMany(mappedBy = "usuarioByIdUsuario")
    private Collection<ListausuarioEntity> listausuariosByIdUsuario;
    @OneToMany(mappedBy = "usuarioByDueno")
    private Collection<NotificacionEntity> notificacionsByIdUsuario;
    @OneToMany(mappedBy = "usuarioByIdComprador")
    private Collection<ProductoEntity> productosByIdUsuario;
    @OneToMany(mappedBy = "usuarioByUsuarioIdUsuario")
    private Collection<ProductosFavoritosEntity> productosFavoritosByIdUsuario;
    @OneToMany(mappedBy = "usuarioByMayorPostor")
    private Collection<SubastaEntity> subastasByIdUsuario;
    @OneToMany(mappedBy = "usuarioByCreador")
    private Collection<SubastaEntity> subastasByIdUsuario_0;
    @ManyToOne
    @JoinColumn(name = "genero", referencedColumnName = "Genero", nullable = false)
    private GeneroEntity generoByGenero;
    @ManyToOne
    @JoinColumn(name = "tipoUsuario", referencedColumnName = "tipoUsuario", nullable = false)
    private TipousuarioEntity tipousuarioByTipoUsuario;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsuarioEntity that = (UsuarioEntity) o;

        if (idUsuario != that.idUsuario) return false;
        if (nombreUsuario != null ? !nombreUsuario.equals(that.nombreUsuario) : that.nombreUsuario != null)
            return false;
        if (contrasena != null ? !contrasena.equals(that.contrasena) : that.contrasena != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (apellidos != null ? !apellidos.equals(that.apellidos) : that.apellidos != null) return false;
        if (domicilio != null ? !domicilio.equals(that.domicilio) : that.domicilio != null) return false;
        if (ciudad != null ? !ciudad.equals(that.ciudad) : that.ciudad != null) return false;
        if (edad != null ? !edad.equals(that.edad) : that.edad != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUsuario;
        result = 31 * result + (nombreUsuario != null ? nombreUsuario.hashCode() : 0);
        result = 31 * result + (contrasena != null ? contrasena.hashCode() : 0);
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (apellidos != null ? apellidos.hashCode() : 0);
        result = 31 * result + (domicilio != null ? domicilio.hashCode() : 0);
        result = 31 * result + (ciudad != null ? ciudad.hashCode() : 0);
        result = 31 * result + (edad != null ? edad.hashCode() : 0);
        return result;
    }

    public Collection<CategoriasfavoritasEntity> getCategoriasfavoritasByIdUsuario() {
        return categoriasfavoritasByIdUsuario;
    }

    public void setCategoriasfavoritasByIdUsuario(Collection<CategoriasfavoritasEntity> categoriasfavoritasByIdUsuario) {
        this.categoriasfavoritasByIdUsuario = categoriasfavoritasByIdUsuario;
    }

    public Collection<ListausuarioEntity> getListausuariosByIdUsuario() {
        return listausuariosByIdUsuario;
    }

    public void setListausuariosByIdUsuario(Collection<ListausuarioEntity> listausuariosByIdUsuario) {
        this.listausuariosByIdUsuario = listausuariosByIdUsuario;
    }

    public Collection<NotificacionEntity> getNotificacionsByIdUsuario() {
        return notificacionsByIdUsuario;
    }

    public void setNotificacionsByIdUsuario(Collection<NotificacionEntity> notificacionsByIdUsuario) {
        this.notificacionsByIdUsuario = notificacionsByIdUsuario;
    }

    public Collection<ProductoEntity> getProductosByIdUsuario() {
        return productosByIdUsuario;
    }

    public void setProductosByIdUsuario(Collection<ProductoEntity> productosByIdUsuario) {
        this.productosByIdUsuario = productosByIdUsuario;
    }

    public Collection<ProductosFavoritosEntity> getProductosFavoritosByIdUsuario() {
        return productosFavoritosByIdUsuario;
    }

    public void setProductosFavoritosByIdUsuario(Collection<ProductosFavoritosEntity> productosFavoritosByIdUsuario) {
        this.productosFavoritosByIdUsuario = productosFavoritosByIdUsuario;
    }

    public Collection<SubastaEntity> getSubastasByIdUsuario() {
        return subastasByIdUsuario;
    }

    public void setSubastasByIdUsuario(Collection<SubastaEntity> subastasByIdUsuario) {
        this.subastasByIdUsuario = subastasByIdUsuario;
    }

    public Collection<SubastaEntity> getSubastasByIdUsuario_0() {
        return subastasByIdUsuario_0;
    }

    public void setSubastasByIdUsuario_0(Collection<SubastaEntity> subastasByIdUsuario_0) {
        this.subastasByIdUsuario_0 = subastasByIdUsuario_0;
    }

    public GeneroEntity getGeneroByGenero() {
        return generoByGenero;
    }

    public void setGeneroByGenero(GeneroEntity generoByGenero) {
        this.generoByGenero = generoByGenero;
    }

    public TipousuarioEntity getTipousuarioByTipoUsuario() {
        return tipousuarioByTipoUsuario;
    }

    public void setTipousuarioByTipoUsuario(TipousuarioEntity tipousuarioByTipoUsuario) {
        this.tipousuarioByTipoUsuario = tipousuarioByTipoUsuario;
    }

    public UsuarioDTO toDTO(){
        UsuarioDTO u = new UsuarioDTO();
        u.setApellidos(this.getApellidos());
        u.setCiudad(this.getCiudad());
        u.setContrasena(this.getContrasena());
        u.setDomicilio(this.getDomicilio());
        u.setEdad(this.getEdad());
        u.setGenero(new GeneroDTO(this.getGeneroByGenero().getGenero()));
        u.setIdUsuario(this.getIdUsuario());
        u.setNombre(this.getNombre());
        u.setNombreUsuario(this.getNombreUsuario());
        u.setTipoUsuario(new TipousuarioDTO(this.getTipousuarioByTipoUsuario().getTipoUsuario()));

        return u;
    }
}
