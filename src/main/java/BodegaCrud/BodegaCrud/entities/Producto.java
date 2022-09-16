
package BodegaCrud.BodegaCrud.entities;

import BodegaCrud.BodegaCrud.utilerias.Elaboracion;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(name = "codigo", nullable = false)
    private String codigo;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "precio_venta", nullable = false)
    private double precioVenta;   
    
    @Column(name = "stock", nullable = false)
    private int stock;      
    
    @Column(name = "estado", nullable = false)
    private String estado;  

    @Column(name = "tipo_elaborado", nullable = false)
    private String tipoElaborado;
    
    @Column(name = "es_defectuoso", nullable = false)
    private String esDefectuoso;
    
    @ManyToOne(fetch = FetchType.EAGER)    
    @JoinColumn(name = "categori_id",nullable = false )
    private Categoria categoria;
    
    
}
