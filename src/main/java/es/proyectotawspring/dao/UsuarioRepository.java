package es.proyectotawspring.dao;

import es.proyectotawspring.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    Optional<UsuarioEntity> findUsuarioByNombreUsuarioAndContrasena(String nombreUsuario, String contrasena);
    @Query("select a from UsuarioEntity a where a.nombre like CONCAT('%', :busqueda, '%')")
    List<UsuarioEntity> findAllByNombreLike(@Param("busqueda") String like);
    @Query("select a from UsuarioEntity a where a.apellidos like CONCAT('%', :busqueda, '%')")
    List<UsuarioEntity> findAllByApellidosLike(@Param("busqueda") String like);
    @Query("select a from UsuarioEntity a where a.domicilio like CONCAT('%', :busqueda, '%')")
    List<UsuarioEntity> findAllByDomicilioLike(@Param("busqueda") String like);
    @Query("select a from UsuarioEntity a where a.ciudad like CONCAT('%', :busqueda, '%')")
    List<UsuarioEntity> findAllByCiudadLike(@Param("busqueda") String like);
    List<UsuarioEntity> findAllByEdad(Integer like);
    @Query("select a from UsuarioEntity a where a.genero.genero like CONCAT('%', :busqueda, '%')")
    List<UsuarioEntity> findAllByGeneroLike(@Param("busqueda") String like);
    @Query("select a from UsuarioEntity a where a.tipoUsuario.tipoUsuario like CONCAT('%', :busqueda, '%')")
    List<UsuarioEntity> findAllByTipoUsuarioLike(@Param("busqueda") String like);
    @Query("select a from UsuarioEntity a where a.nombreUsuario like CONCAT('%', :busqueda, '%')")
    List<UsuarioEntity> findAllByNombreUsuarioLike(@Param("busqueda") String like);
}
