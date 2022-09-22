
package BodegaCrud.BodegaCrud.service;

import BodegaCrud.BodegaCrud.dto.CategoriaDTO;
import BodegaCrud.BodegaCrud.entities.Categoria;
import BodegaCrud.BodegaCrud.excepciones.ResourceNotFoundException;
import BodegaCrud.BodegaCrud.repository.CategoriaRepository;
import BodegaCrud.BodegaCrud.service.Interfaces.ICategoriaService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements ICategoriaService{

    private final CategoriaRepository categoriaRepository;
    private final ModelMapper modelMapper;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository,
            ModelMapper modelMapper) {
        this.categoriaRepository = categoriaRepository;
        this.modelMapper = modelMapper;
    }      
    
    @Override
    public CategoriaDTO createCategoria(CategoriaDTO categoriaDTO) {       
        return mapearToCategoriaDTO(categoriaRepository.save(mapearToCategoria(categoriaDTO)));
    }

    @Override
    public CategoriaDTO updateCategoria(CategoriaDTO categoriaDTO, long categoriaId) {
        
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", "id", categoriaId));
        
        categoria.setNombre(categoriaDTO.getNombre());
        categoria.setDescripcion(categoriaDTO.getDescripcion());
        categoria.setEstado(categoriaDTO.getEstado());
        
        return mapearToCategoriaDTO(categoria);
    }

    @Override
    public void deleteCategoriaById(long categoriaId) {
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", "id", categoriaId));
        
        categoriaRepository.delete(categoria);
    }

    @Override
    public List<CategoriaDTO> getAllCategorias() {
        
        List<Categoria> listCategorias = categoriaRepository.findAll();
        
        return listCategorias.stream().map(categoria -> mapearToCategoriaDTO(categoria)).collect(Collectors.toList());
    }
    
    
    @Override
    public CategoriaDTO getCategoriasById(long categoriaId) {
        
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", "id", categoriaId));
        
        return mapearToCategoriaDTO(categoria);       
    }
    
    private Categoria mapearToCategoria(CategoriaDTO categoriaDTO){
        
        return modelMapper.map(categoriaDTO, Categoria.class);
    }

    private CategoriaDTO mapearToCategoriaDTO(Categoria categoria){
        
        return modelMapper.map(categoria, CategoriaDTO.class);
    }
            
}
