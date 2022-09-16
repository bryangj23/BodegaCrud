
package BodegaCrud.BodegaCrud.service;

import BodegaCrud.BodegaCrud.dto.ProductoCreateDTO;
import BodegaCrud.BodegaCrud.dto.ProductoDTO;
import BodegaCrud.BodegaCrud.dto.ProductosPageDTO;
import BodegaCrud.BodegaCrud.entities.Producto;


public interface IProductoService {
 
    public ProductoDTO updateProducto(ProductoCreateDTO productoCreateDTO, long id) ;
    public Producto getProductosById(long id);
    public ProductosPageDTO getAllProductos(int pageNumber, int pageSize, String orderBy, String sortDir);    
    public ProductoDTO createProducto(ProductoCreateDTO productoDTO);
    public void deleteProductos(long id);   
   
}
