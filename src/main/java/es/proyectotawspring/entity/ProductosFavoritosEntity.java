package es.proyectotawspring.entity;

import javax.persistence.*;
import java.util.Objects;

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
        return usuarioIdUsuario == that.usuarioIdUsuario && productoIdProducto == that.productoIdProducto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioIdUsuario, productoIdProducto);
    }
}
