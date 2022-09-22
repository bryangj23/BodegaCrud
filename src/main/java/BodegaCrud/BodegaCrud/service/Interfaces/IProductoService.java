
package BodegaCrud.BodegaCrud.service.Interfaces;

import BodegaCrud.BodegaCrud.dto.ProductoCategoriaDTO;
import BodegaCrud.BodegaCrud.dto.ProductoDTO;
import BodegaCrud.BodegaCrud.dto.ProductosPageDTO;
import java.util.List;

public interface IProductoService {
 
    public ProductoDTO updateProducto(long categoriaId, ProductoDTO productoDTO, long id) ;
    
    public ProductoDTO getProductosById(long id);
    
    public ProductosPageDTO getAllProductos(int pageNumber, int pageSize, String orderBy, String sortDir);    
    
    public ProductosPageDTO getAllProductosFiltro(int pageNumber, int pageSize, String estado, String tipoElaborado, String esDefectuoso);    
    
    public ProductoDTO createProducto(long categoriaId, ProductoDTO productoDTO);
    
    public String insertListProductos(List<ProductoCategoriaDTO> listProductosDTO); 
    
    public void deleteProductos(long id);    
   
    public List<ProductoDTO> getProdutosByCategoriaId(long categoriaId);
}
