
package BodegaCrud.BodegaCrud.service.Interfaces;

import BodegaCrud.BodegaCrud.dto.RegistroUserDTO;
import BodegaCrud.BodegaCrud.entities.Usuario;
import java.util.List;

public interface IUsuarioService {
    
    public String createUsuario(RegistroUserDTO registroUserDTO);
    
    public List<Usuario> listUsuarios();
    
}
