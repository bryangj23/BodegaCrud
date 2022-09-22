
package BodegaCrud.BodegaCrud.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Usuarios", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"}), 
    @UniqueConstraint(columnNames = {"email"})})
@Getter @Setter
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 150)
    private String email;
    
    @Column(length = 60)    
    private String nombre;
    
    @Column(length = 85)
    private String password;
    
    @Column(length = 60)
    private String username;
}
