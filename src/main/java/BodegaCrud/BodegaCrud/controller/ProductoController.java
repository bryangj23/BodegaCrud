
package BodegaCrud.BodegaCrud.controller;

import BodegaCrud.BodegaCrud.dto.ProductoCreateDTO;
import BodegaCrud.BodegaCrud.dto.ProductoDTO;
import BodegaCrud.BodegaCrud.dto.ProductosPageDTO;
import BodegaCrud.BodegaCrud.entities.Producto;
import BodegaCrud.BodegaCrud.service.IProductoService;
import BodegaCrud.BodegaCrud.utilerias.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/productos")
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

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(productoService.getProductosById(id));
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> createPublication(@RequestBody ProductoCreateDTO productoCreateDTO) {
        return new ResponseEntity<>(productoService.createProducto(productoCreateDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> updateProducto(@RequestBody ProductoCreateDTO productoCreateDTO, @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(productoService.updateProducto(productoCreateDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePublication(@PathVariable(name = "id") long id) {
        productoService.deleteProductos(id);
        return new ResponseEntity<>("Producto eliminado", HttpStatus.OK);
    }
}
