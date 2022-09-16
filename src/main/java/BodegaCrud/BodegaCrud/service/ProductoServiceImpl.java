/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BodegaCrud.BodegaCrud.service;

import BodegaCrud.BodegaCrud.dto.ProductoCreateDTO;
import BodegaCrud.BodegaCrud.dto.ProductoDTO;
import BodegaCrud.BodegaCrud.dto.ProductosPageDTO;
import BodegaCrud.BodegaCrud.entities.Categoria;
import BodegaCrud.BodegaCrud.entities.Producto;
import BodegaCrud.BodegaCrud.excepciones.ResourceNotFoundException;
import BodegaCrud.BodegaCrud.repository.ProductoRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements IProductoService{
    
    private final ProductoRepository productoRepository;    
    
    public ProductoServiceImpl(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }
    
    @Override
    public ProductoDTO createProducto(ProductoCreateDTO productoCreateDTO) {
        
        Categoria categoria = new Categoria();
        categoria.setId(productoCreateDTO.getIdcategoria());
        
        Producto producto = new Producto();
        producto.setCodigo(productoCreateDTO.getCodigo());
        producto.setNombre(productoCreateDTO.getNombre());
        producto.setDescripcion(productoCreateDTO.getDescripcion());
        producto.setEstado(productoCreateDTO.getEstado());
        producto.setPrecioVenta(productoCreateDTO.getPrecioVenta());
        producto.setStock(productoCreateDTO.getStock());
        producto.setTipoElaborado(productoCreateDTO.getTipoElaborado());
        producto.setEsDefectuoso(productoCreateDTO.getEsDefectuoso());
        producto.setCategoria(categoria);        
        
        return mapearDTO(productoRepository.save(producto));
    }

    @Override
    public ProductosPageDTO getAllProductos(int pageNumber, int pageSize, String orderBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending():Sort.by(orderBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        ProductosPageDTO productosPage = new ProductosPageDTO();
         
        Page<Producto> pagesProductos = productoRepository.findAll(pageable);
        List<Producto> productos = pagesProductos.getContent();        
        productosPage.setContents(productos.stream().map(producto -> mapearDTO(producto)).collect(Collectors.toList()));
        productosPage.setPageNumber(pagesProductos.getNumber());
        productosPage.setPageSize(pagesProductos.getSize());
        productosPage.setTotalElements(pagesProductos.getTotalElements());
        productosPage.setTotalPages(pagesProductos.getTotalPages());
        productosPage.setIsLast(pagesProductos.isLast());
        
        return productosPage;
    }

    @Override
    public Producto getProductosById(long id) {

        Producto newProducto = productoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Producto", "id", id));
       System.out.println(newProducto);
        return newProducto;
    }

    @Override
    public ProductoDTO updateProducto(ProductoCreateDTO productoCreateDTO, long id) {

        ProductoDTO productoDTOResponse = new ProductoDTO();
        Producto updateProducto = productoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Producto", "id", id));
        
        Categoria categoria = new Categoria();
        categoria.setId(productoCreateDTO.getIdcategoria());
        
        updateProducto.setId(productoCreateDTO.getId());
        updateProducto.setNombre(productoCreateDTO.getNombre());
        updateProducto.setCodigo(productoCreateDTO.getCodigo());
        updateProducto.setTipoElaborado(productoCreateDTO.getTipoElaborado());
        updateProducto.setPrecioVenta(productoCreateDTO.getPrecioVenta());        
        updateProducto.setDescripcion(productoCreateDTO.getDescripcion());                
        updateProducto.setStock(productoCreateDTO.getStock());
        updateProducto.setEstado(productoCreateDTO.getEstado());
        updateProducto.setEsDefectuoso(productoCreateDTO.getEsDefectuoso());
        updateProducto.setCategoria(categoria);        
        
        productoDTOResponse = mapearDTO(productoRepository.save(updateProducto));
        
        return productoDTOResponse;
    }
    
    @Override
    public void deleteProductos(long id){
        Producto deleteProducto = productoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Producto", "id", id));
        productoRepository.delete(deleteProducto);
    }
    
    //Convert Producto to ProductoDTO
    private ProductoDTO mapearDTO(Producto producto) {
        ProductoDTO productoDTO = new ProductoDTO();

        productoDTO.setId(producto.getId());
        productoDTO.setNombre(producto.getNombre());
        productoDTO.setCodigo(producto.getCodigo());
        productoDTO.setTipoElaborado(producto.getTipoElaborado());
        productoDTO.setPrecioVenta(producto.getPrecioVenta());        
        productoDTO.setDescripcion(producto.getDescripcion());        
        productoDTO.setTipoElaborado(producto.getTipoElaborado());
        productoDTO.setStock(producto.getStock());
        productoDTO.setEstado(producto.getEstado());
        productoDTO.setEsDefectuoso(producto.getEsDefectuoso());
        productoDTO.setCategoria(producto.getCategoria());
        
        return productoDTO;
    }

   
    private Producto mapearEntity(ProductoDTO productoDTO) {
        Producto producto = new Producto();

        producto.setId(productoDTO.getId());
        producto.setNombre(productoDTO.getNombre());
        producto.setCodigo(productoDTO.getCodigo());
        producto.setTipoElaborado(productoDTO.getTipoElaborado());
        producto.setPrecioVenta(productoDTO.getPrecioVenta());        
        producto.setDescripcion(productoDTO.getDescripcion());        
        producto.setTipoElaborado(productoDTO.getTipoElaborado());
        producto.setStock(productoDTO.getStock());
        producto.setEstado(productoDTO.getEstado());
        producto.setEsDefectuoso(productoDTO.getEsDefectuoso());
        producto.setCategoria(productoDTO.getCategoria());
        
        return producto;
    }    
}
