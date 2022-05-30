package es.proyectotawspring.dao;

import es.proyectotawspring.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity,Integer> {
    List<CategoriaEntity> findAllByNombreLike (String busqueda);
}
