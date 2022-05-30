package es.proyectotawspring.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class ProductosFavoritosEntityPK implements Serializable {
    @Column(name = "Usuario_idUsuario")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int usuarioIdUsuario;
    @Column(name = "Producto_idProducto")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

        ProductosFavoritosEntityPK that = (ProductosFavoritosEntityPK) o;

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
}
