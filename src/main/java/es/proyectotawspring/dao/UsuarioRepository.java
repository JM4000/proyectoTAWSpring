package es.proyectotawspring.dao;

import es.proyectotawspring.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    Optional<UsuarioEntity> findUsuarioByNombreUsuarioAndContrasena(String nombreUsuario, String contrasena);
    List<UsuarioEntity> findAllByNombreLike(String like);
    List<UsuarioEntity> findAllByApellidosLike(String like);
    List<UsuarioEntity> findAllByDomicilioLike(String like);
    List<UsuarioEntity> findAllByCiudadLike(String like);
    List<UsuarioEntity> findAllByEdad(Integer edad);
    List<UsuarioEntity> findAllByGeneroLike(String like);
    List<UsuarioEntity> findAllByTipoUsuarioLike(String like);
    List<UsuarioEntity> findAllByNombreUsuarioLike(String Like);
}
