package es.proyectotawspring.dao;

import es.proyectotawspring.entity.NotificacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<NotificacionEntity, Integer> {
    List<NotificacionEntity> findAllByDueno(Integer dueno);
}
