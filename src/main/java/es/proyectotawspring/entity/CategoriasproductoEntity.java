package es.proyectotawspring.entity;

import javax.persistence.*;

@Entity
@Table(name = "categoriasproducto", schema = "proyectotaw", catalog = "")
@IdClass(CategoriasproductoEntityPK.class)
public class CategoriasproductoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idProducto")
    private int idProducto;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idCategoria")
    private int idCategoria;
    @ManyToOne
    @JoinColumn(name = "idProducto", referencedColumnName = "idProducto", nullable = false)
    private ProductoEntity productoByIdProducto;
    @ManyToOne
    @JoinColumn(name = "idCategoria", referencedColumnName = "idCategoria", nullable = false)
    private CategoriaEntity categoriaByIdCategoria;

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoriasproductoEntity that = (CategoriasproductoEntity) o;

        if (idProducto != that.idProducto) return false;
        if (idCategoria != that.idCategoria) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idProducto;
        result = 31 * result + idCategoria;
        return result;
    }

    public ProductoEntity getProductoByIdProducto() {
        return productoByIdProducto;
    }

    public void setProductoByIdProducto(ProductoEntity productoByIdProducto) {
        this.productoByIdProducto = productoByIdProducto;
    }

    public CategoriaEntity getCategoriaByIdCategoria() {
        return categoriaByIdCategoria;
    }

    public void setCategoriaByIdCategoria(CategoriaEntity categoriaByIdCategoria) {
        this.categoriaByIdCategoria = categoriaByIdCategoria;
    }
}
