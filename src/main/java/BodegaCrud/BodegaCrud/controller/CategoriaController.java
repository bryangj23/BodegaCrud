
package BodegaCrud.BodegaCrud.controller;

import BodegaCrud.BodegaCrud.dto.CategoriaDTO;
import BodegaCrud.BodegaCrud.service.Interfaces.ICategoriaService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    
    private final ICategoriaService categoriaService;
    
    public CategoriaController(ICategoriaService categoriaService){
        this.categoriaService = categoriaService;
    }
    
    @GetMapping()
    public ResponseEntity<List<CategoriaDTO>> getListAll(){
        return new ResponseEntity<>(categoriaService.getAllCategorias(), HttpStatus.OK);
    }
    
}
