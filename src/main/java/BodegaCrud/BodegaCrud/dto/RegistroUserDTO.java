
package BodegaCrud.BodegaCrud.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegistroUserDTO {
    
    @NotNull
    private String nombre;
    
    @NotNull
    private String email;
    
    @NotNull
    private String username;
    
    @Email
    private String password;
    
    
    
}
