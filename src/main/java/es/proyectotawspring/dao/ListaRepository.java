package es.proyectotawspring.dao;

import es.proyectotawspring.entity.ListaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListaRepository extends JpaRepository<ListaEntity, Integer> {
    @Query("select a from ListaEntity a where a.nombre like CONCAT('%', :busqueda, '%')")
    List<ListaEntity> findAllByNombreLike(String like);
}
