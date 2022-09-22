
package BodegaCrud.BodegaCrud.service.Interfaces;

import BodegaCrud.BodegaCrud.dto.CategoriaDTO;
import java.util.List;

public interface ICategoriaService {
    
    public CategoriaDTO createCategoria(CategoriaDTO categoriaDTO);
    
    public CategoriaDTO updateCategoria(CategoriaDTO categoriaDTO, long categoriaId);
    
    public void deleteCategoriaById(long categoriaId);
    
    public List<CategoriaDTO> getAllCategorias();
    
    public CategoriaDTO getCategoriasById(long categoriaId);
    
    
}
