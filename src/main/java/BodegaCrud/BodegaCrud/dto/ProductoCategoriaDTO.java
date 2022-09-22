
package BodegaCrud.BodegaCrud.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class ProductoCategoriaDTO {

    private String nombre;    
    private String codigo;  
    private String descripcion;
    private double precioVenta;
    private int stock;
    private String estado;  
    private String tipoElaborado;
    private String esDefectuoso;
    private Long idcategoria;
    
}
