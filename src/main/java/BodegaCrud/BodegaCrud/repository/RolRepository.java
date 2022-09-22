package BodegaCrud.BodegaCrud.repository;

import BodegaCrud.BodegaCrud.entities.Rol;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{
    
    public Optional<Rol> findByNombre(String nombre);
    
    @Query(value = "SELECT "
            + "R.id,"
            + "R.nombre"
            + " FROM Usuarios U"
            + " INNER JOIN Usuario_roles UR ON U.Id = UR.Id"
            + " INNER JOIN Roles R ON UR.Id = R.Id"
            + " WHERE U.Id = :usuarioId", nativeQuery = true)
    public Set<Rol> getRolByUsuarioId(@Param("usuarioId") Long usuarioId);
    
}
