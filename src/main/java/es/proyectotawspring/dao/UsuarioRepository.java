package es.proyectotawspring.dao;

import es.proyectotawspring.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    public UsuarioEntity findUsuarioByNombreUsuarioAndContrasena(String nombreUsuario, String contrasena);
}
