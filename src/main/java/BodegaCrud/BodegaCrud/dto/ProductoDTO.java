
package BodegaCrud.BodegaCrud.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ProductoDTO {
    
    private Long id;
    
    @NotEmpty
    @Size(min = 2, message = "El nombre del producto deberia ser al menos de 2 caracteres")
    private String nombre;    
    
    @NotEmpty
    @Size(min = 4,max = 10, message = "El codigo del producto deberia ser de min: 4 y max: 10 caracteres")
    private String codigo;  
    
    @NotEmpty
    @Size(min = 10,max = 150, message = "La descripcion del producto deberia ser de min: 10 y max: 150 caracteres")
    private String descripcion;
    
    private double precioVenta;
    
    private int stock;
    
    @NotEmpty
    @Size(min = 1,max = 2, message = "El estado del producto deberia ser de min: 1 y max: 2 caracteres")
    private String estado;  
    
    @NotEmpty
    private String tipoElaborado;
    
    @NotEmpty
    @Size(min = 1,max = 2, message = "esDefectuoso del producto deberia ser de min: 1 y max: 2 caracteres")
    private String esDefectuoso;
}
