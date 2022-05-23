package es.proyectotawspring.dao;

import es.proyectotawspring.entity.GeneroEntity;
import es.proyectotawspring.entity.TipousuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipousuarioEntity, String> {

}
