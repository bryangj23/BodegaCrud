
package BodegaCrud.BodegaCrud.seguridad;

import BodegaCrud.BodegaCrud.entities.Rol;
import BodegaCrud.BodegaCrud.entities.Usuario;
import BodegaCrud.BodegaCrud.repository.UsuarioRepository;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import BodegaCrud.BodegaCrud.repository.RolRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

    
    public final UsuarioRepository usuarioRepository;
    public final RolRepository rolRepository;
    
    public CustomUserDetailService(UsuarioRepository usuarioRepository, 
            RolRepository rolRepository){
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }    
    
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        
        Usuario usuario = usuarioRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).
                orElseThrow(() -> new UsernameNotFoundException("Usuario no encontado con usernam o email: "+ usernameOrEmail ));
        
        Set<Rol> rolesUsuario = rolRepository.getRolByUsuarioId(usuario.getId());
        
        return new User(usuario.getEmail(), usuario.getPassword(), mapearRoles(rolesUsuario));
    }
    
    private Collection<? extends GrantedAuthority> mapearRoles(Set<Rol> roles){
        return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                .collect(Collectors.toList());
    }
}
