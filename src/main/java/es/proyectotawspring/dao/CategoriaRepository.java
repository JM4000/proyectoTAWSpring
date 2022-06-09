package es.proyectotawspring.dao;

import es.proyectotawspring.entity.CategoriaEntity;
import es.proyectotawspring.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity,Integer> {
    List<CategoriaEntity> findAllByNombre(String busqueda);

    CategoriaEntity findByNombre(String nombre);

    @Query("select a from CategoriaEntity a where a.nombre like CONCAT('%', :busqueda, '%')")
    List<CategoriaEntity> findAllByNombreLike(@Param("busqueda") String like);


}
