
package BodegaCrud.BodegaCrud.controller;

import BodegaCrud.BodegaCrud.dto.ProductoCategoriaDTO;
import BodegaCrud.BodegaCrud.dto.ProductoDTO;
import BodegaCrud.BodegaCrud.dto.ProductosPageDTO;
import BodegaCrud.BodegaCrud.service.Interfaces.IProductoService;
import BodegaCrud.BodegaCrud.utilerias.AppConstants;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    
    private final IProductoService productoService;            
    
    public ProductoController(IProductoService productService){
        this.productoService = productService;
    }
    
    @GetMapping
    public ProductosPageDTO getListProductos(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int pageSize,
            @RequestParam(value = "orderBy", defaultValue = AppConstants.ORDENAR_POR_DEFECTO, required = false) String orderBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.ORDENAR_DIRECCION_DEFECTO, required = false) String sortDir){
        
        return productoService.getAllProductos(pageNumber, pageSize, orderBy, sortDir);
    }
    
    
    @GetMapping("/filtro")
    public ProductosPageDTO getListProductosFiltro(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.MEDIDA_DE_PAGINA_POR_DEFECTO, required = false) int pageSize,
            @RequestParam(value = "estado", defaultValue = AppConstants.FILTRAR_ESTADO_POR_DEFECTO, required = false) String estado,
            @RequestParam(value = "tipoElaborado", defaultValue = AppConstants.FILTRAR_TIPOELABORADO_POR_DEFECTO, required = false) String tipoElaborado,
            @RequestParam(value = "esDefectuoso", defaultValue = AppConstants.FILTRAR_ESDEFECTUOSO_POR_DEFECTO, required = false) String esDefectuoso){
        
        return productoService.getAllProductosFiltro(pageNumber, pageSize, estado, tipoElaborado, esDefectuoso);
    }
    
    @GetMapping("/categorias/{categoriaId}/productos")
    public ResponseEntity<List<ProductoDTO>> getProductosByCategoriaId(
            @PathVariable(name = "categoriaId") long categoriaId){
        
        return new ResponseEntity<>(productoService.getProdutosByCategoriaId(categoriaId), HttpStatus.OK);
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<ProductoDTO> getProductoById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(productoService.getProductosById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("categorias/{categoriaId}/productos")
    public ResponseEntity<ProductoDTO> createProducto(
            @PathVariable(value = "categoriaId")long categoriaId, 
            @Valid @RequestBody ProductoDTO productoDTO) {
        return new ResponseEntity<>(productoService.createProducto(categoriaId,productoDTO), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("categorias/{categoriaId}/productos/{idProducto}")
    public ResponseEntity<ProductoDTO> updateProducto(
            @PathVariable(value = "categoriaId") long categoriaId, 
            @Valid @RequestBody ProductoDTO productoDTO, 
            @PathVariable(name = "idProducto") long idProducto) {
        return new ResponseEntity<>(productoService.updateProducto(categoriaId, productoDTO, idProducto), HttpStatus.OK);
    }

    @DeleteMapping("/deleteproductobyid/{id}")
    public ResponseEntity<String> deleteProducto(@Valid @PathVariable(value = "id") long id) {
        productoService.deleteProductos(id);
        return new ResponseEntity<>("Producto eliminado", HttpStatus.OK);
    }
    
    @PostMapping("/cargueProductos")
    public ResponseEntity<String> cargueProductos(@RequestBody List<ProductoCategoriaDTO> productosDTO){
        return new ResponseEntity<>(productoService.insertListProductos(productosDTO), HttpStatus.CREATED);
    }
    
}
