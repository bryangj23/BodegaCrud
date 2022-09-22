package BodegaCrud.BodegaCrud.service;

import BodegaCrud.BodegaCrud.dto.RegistroUserDTO;
import BodegaCrud.BodegaCrud.entities.Rol;
import BodegaCrud.BodegaCrud.entities.Usuario;
import BodegaCrud.BodegaCrud.entities.UsuarioRoles;
import BodegaCrud.BodegaCrud.excepciones.ResourceNotFoundException;
import BodegaCrud.BodegaCrud.repository.RolRepository;
import BodegaCrud.BodegaCrud.repository.UsuarioRepository;
import BodegaCrud.BodegaCrud.repository.UsuarioRolesRepository;
import BodegaCrud.BodegaCrud.service.Interfaces.IUsuarioService;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioRolesRepository usuarioRolesRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository,
            UsuarioRolesRepository usuarioRolesRepository,
            RolRepository rolRepository,
            PasswordEncoder passwordEncoder,
            ModelMapper modelMapper) {
        
        this.usuarioRepository = usuarioRepository;
        this.usuarioRolesRepository = usuarioRolesRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public String createUsuario(RegistroUserDTO registroUserDTO) {

        if (usuarioRepository.existsByEmail(registroUserDTO.getEmail())) {
            throw new ResourceNotFoundException("El email de usuario ya existe");
        }
        if (usuarioRepository.existsByUsername(registroUserDTO.getUsername())) {
            throw new ResourceNotFoundException("El user name de usuario ya existe");
        }

        Usuario registrarUsuario = new Usuario();
        UsuarioRoles usuarioRoles = new UsuarioRoles();
        
        registrarUsuario.setNombre(registroUserDTO.getNombre());
        registrarUsuario.setEmail(registroUserDTO.getEmail());
        registrarUsuario.setUsername(registroUserDTO.getUsername());
        registrarUsuario.setPassword(passwordEncoder.encode(registroUserDTO.getPassword()));
        
        Rol rol = rolRepository.findByNombre("ROLE_USER").get();
        
        registrarUsuario = usuarioRepository.save(registrarUsuario);
        
        usuarioRoles.setRolId(rol);
        usuarioRoles.setUsuarioId(registrarUsuario);
        usuarioRolesRepository.save(usuarioRoles);

        return "Usuario ha sido registrado";
    }
    
    @Override
    public List<Usuario> listUsuarios(){
        return usuarioRepository.findAll();
    }  

}
