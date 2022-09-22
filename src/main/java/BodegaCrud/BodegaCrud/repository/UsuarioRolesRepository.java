package BodegaCrud.BodegaCrud.repository;

import BodegaCrud.BodegaCrud.entities.UsuarioRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRolesRepository extends JpaRepository<UsuarioRoles, Long>{
    
}
