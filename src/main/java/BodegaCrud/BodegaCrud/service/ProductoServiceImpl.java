package BodegaCrud.BodegaCrud.service;

import BodegaCrud.BodegaCrud.dto.ProductoCategoriaDTO;
import BodegaCrud.BodegaCrud.service.Interfaces.IProductoService;
import BodegaCrud.BodegaCrud.dto.ProductoDTO;
import BodegaCrud.BodegaCrud.dto.ProductosPageDTO;
import BodegaCrud.BodegaCrud.entities.Categoria;
import BodegaCrud.BodegaCrud.entities.Producto;
import BodegaCrud.BodegaCrud.excepciones.ResourceNotFoundException;
import BodegaCrud.BodegaCrud.repository.CategoriaRepository;
import BodegaCrud.BodegaCrud.repository.ProductoRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements IProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ModelMapper modelMapper;
    private static final int TAMANOPORLOTES = 30;

    public ProductoServiceImpl(ProductoRepository productoRepository,
            CategoriaRepository categoriaRepository,
            ModelMapper modelMapper) {

        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductoDTO createProducto(long categoriaId, ProductoDTO productoDTO) {

        Producto producto = mapearEntity(productoDTO);

        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", "id", categoriaId));
        producto.setCategoria(categoria);

        producto.setCategoria(categoria);
        return mapearDTO(productoRepository.save(producto));
    }

    @Override
    public ProductosPageDTO getAllProductos(int pageNumber, int pageSize, String orderBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();

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
    public List<ProductoDTO> getProdutosByCategoriaId(long categoriaId) {

        List<Producto> listProdByCate = productoRepository.findByCategoriaId(categoriaId);

        return listProdByCate.stream().map(producto -> mapearDTO(producto)).collect(Collectors.toList());
    }

    @Override
    public ProductoDTO getProductosById(long id) {

        Producto newProducto = productoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Producto", "id", id));

        return mapearDTO(newProducto);
    }

    @Override
    public ProductoDTO updateProducto(long categoriaId, ProductoDTO productoDTO, long id) {

        Producto updateProducto = productoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Producto", "id", id));
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", "id", categoriaId));

        updateProducto.setNombre(productoDTO.getNombre());
        updateProducto.setCodigo(productoDTO.getCodigo());
        updateProducto.setTipoElaborado(productoDTO.getTipoElaborado());
        updateProducto.setPrecioVenta(productoDTO.getPrecioVenta());
        updateProducto.setDescripcion(productoDTO.getDescripcion());
        updateProducto.setStock(productoDTO.getStock());
        updateProducto.setEstado(productoDTO.getEstado());
        updateProducto.setEsDefectuoso(productoDTO.getEsDefectuoso());

        updateProducto.setCategoria(categoria);

        return mapearDTO(productoRepository.save(updateProducto));
    }

    @Override
    public void deleteProductos(long id) {
        Producto deleteProducto = productoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Producto", "id", id));
        productoRepository.delete(deleteProducto);
    }

    //Convert Producto to ProductoDTO
    private ProductoDTO mapearDTO(Producto producto) {

        return modelMapper.map(producto, ProductoDTO.class);
    }

    private Producto mapearEntity(ProductoDTO productoDTO) {

        return modelMapper.map(productoDTO, Producto.class);
    }
    
    private Producto mapearProductCateDTO(ProductoCategoriaDTO productoDTO){
        
        Producto producto = new Producto();
        
        producto.setNombre(productoDTO.getNombre());
        producto.setCodigo(productoDTO.getCodigo());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecioVenta(productoDTO.getPrecioVenta());
        producto.setEstado(productoDTO.getEstado());
        producto.setStock(productoDTO.getStock());
        producto.setTipoElaborado(productoDTO.getTipoElaborado());
        producto.setEsDefectuoso(productoDTO.getEsDefectuoso());
        
        return producto;
    }
            

    @Override
    public String insertListProductos(List<ProductoCategoriaDTO> listProductosDTO) {
        
        List<Categoria> listaCategorias = categoriaRepository.findAll();
        
        List<Producto> productosInsert = listProductosDTO.stream().map(productoDTO -> {

            Categoria categoria = listaCategorias.stream().filter(cate -> cate.getId() == productoDTO.getIdcategoria()).findFirst().get();

            Producto producto = mapearProductCateDTO(productoDTO);
            producto.setId(0L);
            producto.setCategoria(categoria);            

            return producto;
              
        }).collect(Collectors.toList());

        for (int i = 0; i < productosInsert.size(); i += TAMANOPORLOTES) {

            if (i + TAMANOPORLOTES > productosInsert.size()) {

                List<Producto> productosToInsert = productosInsert.subList(i, productosInsert.size());

                productoRepository.saveAll(productosToInsert);
                break;
            }
            List<Producto> productosToInsert = productosInsert.subList(i, i + TAMANOPORLOTES);

            productoRepository.saveAll(productosToInsert);
        }

        return "Cargue masivo se productos exitoso";
    }

    @Override
    public ProductosPageDTO getAllProductosFiltro(int pageNumber, int pageSize, String estado, String tipoElaborado, String esDefectuoso){        

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        ProductosPageDTO productosPage = new ProductosPageDTO();

        Page<Producto> pagesProductos = 
                productoRepository.getProductoByEstadoAndEsElaboradoAndEsDefectuoso(estado, tipoElaborado, esDefectuoso, pageable);
        List<Producto> productos = pagesProductos.getContent();
        productosPage.setContents(productos.stream().map(producto -> mapearDTO(producto)).collect(Collectors.toList()));
        productosPage.setPageNumber(pagesProductos.getNumber());
        productosPage.setPageSize(pagesProductos.getSize());
        productosPage.setTotalElements(pagesProductos.getTotalElements());
        productosPage.setTotalPages(pagesProductos.getTotalPages());
        productosPage.setIsLast(pagesProductos.isLast());

        return productosPage;
    }

}
