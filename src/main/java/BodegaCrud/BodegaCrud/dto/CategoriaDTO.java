
package BodegaCrud.BodegaCrud.dto;


import BodegaCrud.BodegaCrud.entities.Producto;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CategoriaDTO {
    
    private Long id;
    
    @NotEmpty
    @Size(min = 2, message = "El nombre del la categoria deberia tener al menos 2 caracteres")
    private String nombre;
    
    @NotEmpty
    @Size(min = 2, message = "La descripcion del la categoria deberia tener al menos 10 caracteres")
    private String descripcion;    
    
    @NotEmpty(message = "El estado ne deberia estar vacio")
    private String estado;    
    
    private Set<Producto> productos  =  new HashSet<>();             
}
