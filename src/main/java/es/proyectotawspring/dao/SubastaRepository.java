package es.proyectotawspring.dao;

import es.proyectotawspring.entity.SubastaEntity;
import es.proyectotawspring.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubastaRepository extends JpaRepository<SubastaEntity, Integer> {
    Optional<SubastaEntity> findAllByProducto(int idProducto);
    SubastaEntity findByIdSubasta(Integer idSubasta);

    @Query("SELECT DISTINCT s FROM SubastaEntity s JOIN s.producto p LEFT JOIN p.categoriaList c WHERE s.fechaCierre >= :today AND p.titulo LIKE :busqueda and c.nombre LIKE :categoria")
    Optional<List<SubastaEntity>> findSubastaActivaFiltro(@Param("today") Date date, @Param("busqueda")String titulo, @Param("categoria") String categoria);
    @Query("SELECT DISTINCT s FROM  SubastaEntity s JOIN s.producto p LEFT JOIN p.categoriaList c JOIN p.usuarioList u WHERE u.idUsuario = :idUser AND s.fechaCierre >= :today AND p.titulo LIKE :busqueda and c.nombre LIKE :categoria")
    Optional<List<SubastaEntity>> findSubastaWithFavouriteProductList(@Param("today") Date date, @Param("idUser")int idUsuario, @Param("busqueda") String titulo, @Param("categoria") String categoria);
    @Query("SELECT DISTINCT s FROM  SubastaEntity s JOIN s.producto p LEFT JOIN p.categoriaList c  WHERE p.idComprador =:idUser AND p.titulo LIKE :busqueda and c.nombre LIKE :categoria")
    Optional<List<SubastaEntity>> findSubastaWithProductsComprados(@Param("idUser") UsuarioEntity comprador, @Param("busqueda") String titulo, @Param("categoria") String categoria);

    @Query("SELECT s FROM SubastaEntity s JOIN ProductoEntity  p ON s.producto.idProducto=p.idProducto WHERE s.fechaCierre >= :today and p.idProducto= :idProducto")
     List<SubastaEntity> findSubastaActiva(@Param("today") Date today,@Param("idProducto")Integer idProducto);



}
