
package BodegaCrud.BodegaCrud.repository;

import BodegaCrud.BodegaCrud.entities.Producto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
    
    public List<Producto> findByCategoriaId(long categoriaId);
    
    @Query(value = "SELECT "
            + "* FROM Productos P"
            + " WHERE P.estado = :estado "
                + " AND P.tipo_elaborado = :tipoElaborado"
                + " AND P.es_defectuoso = :esDefectuoso", nativeQuery = true)
    public Page<Producto> getProductoByEstadoAndEsElaboradoAndEsDefectuoso(
            @Param("estado") String estado, 
            @Param("tipoElaborado") String tipoElaborado,
            @Param("esDefectuoso") String esDefectuoso,
            Pageable pageable);
}
