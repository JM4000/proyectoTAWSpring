package es.proyectotawspring.entity;

import javax.persistence.*;

@Entity
@Table(name = "productos_favoritos", schema = "proyectotaw", catalog = "")
@IdClass(ProductosFavoritosEntityPK.class)
public class ProductosFavoritosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Usuario_idUsuario")
    private int usuarioIdUsuario;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Producto_idProducto")
    private int productoIdProducto;
    @ManyToOne
    @JoinColumn(name = "Usuario_idUsuario", referencedColumnName = "idUsuario", nullable = false)
    private UsuarioEntity usuarioByUsuarioIdUsuario;
    @ManyToOne
    @JoinColumn(name = "Producto_idProducto", referencedColumnName = "idProducto", nullable = false)
    private ProductoEntity productoByProductoIdProducto;

    public int getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(int usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    public int getProductoIdProducto() {
        return productoIdProducto;
    }

    public void setProductoIdProducto(int productoIdProducto) {
        this.productoIdProducto = productoIdProducto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductosFavoritosEntity that = (ProductosFavoritosEntity) o;

        if (usuarioIdUsuario != that.usuarioIdUsuario) return false;
        if (productoIdProducto != that.productoIdProducto) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = usuarioIdUsuario;
        result = 31 * result + productoIdProducto;
        return result;
    }

    public UsuarioEntity getUsuarioByUsuarioIdUsuario() {
        return usuarioByUsuarioIdUsuario;
    }

    public void setUsuarioByUsuarioIdUsuario(UsuarioEntity usuarioByUsuarioIdUsuario) {
        this.usuarioByUsuarioIdUsuario = usuarioByUsuarioIdUsuario;
    }

    public ProductoEntity getProductoByProductoIdProducto() {
        return productoByProductoIdProducto;
    }

    public void setProductoByProductoIdProducto(ProductoEntity productoByProductoIdProducto) {
        this.productoByProductoIdProducto = productoByProductoIdProducto;
    }
}
