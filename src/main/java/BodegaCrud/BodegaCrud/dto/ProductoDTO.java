
package BodegaCrud.BodegaCrud.dto;

import BodegaCrud.BodegaCrud.entities.Categoria;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ProductoDTO {
    
    private Long id;
    private String nombre;    
    private String codigo;  
    private String descripcion;
    private double precioVenta;
    private int stock;
    private String estado;  
    private String tipoElaborado;
    private String esDefectuoso;
    private Categoria categoria;
}
