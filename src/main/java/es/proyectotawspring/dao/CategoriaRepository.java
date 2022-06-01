package es.proyectotawspring.dao;

import es.proyectotawspring.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity,Integer> {
    List<CategoriaEntity> findAllByNombre (String busqueda);

    @Query("select a from CategoriaEntity a where a.nombre like CONCAT('%', :busqueda, '%')")
    List<CategoriaEntity> findAllByNombreLike(@Param("busqueda") String like);

}
