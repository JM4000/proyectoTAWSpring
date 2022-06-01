package es.proyectotawspring.dao;

import es.proyectotawspring.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer> {
    @Query("select a from ProductoEntity a where a.titulo like CONCAT('%', :busqueda, '%')")
    Optional<List<ProductoEntity>> findAllByTituloLike(@Param("busqueda") String like);

    @Query("select distinct a from ProductoEntity a JOIN a.categoriaList c WHERE c.nombre like CONCAT('%', :busqueda, '%')")
    Optional<List<ProductoEntity>> findByCategoriaLike(@Param("busqueda") String like);

    @Query("SELECT p FROM UsuarioEntity u JOIN u.productoList p  WHERE u.idUsuario =:idUser AND p.idProducto = :product")
    Optional<List<ProductoEntity>> isProductFavourite(@Param("idUser") int idUsuario, @Param("product") int idProducto);

    @Query("SELECT p FROM SubastaEntity s JOIN s.producto p where s.creador.idUsuario= :id")
    Optional<List<ProductoEntity>> getProductosVendedor(@Param("id")int id);

}
