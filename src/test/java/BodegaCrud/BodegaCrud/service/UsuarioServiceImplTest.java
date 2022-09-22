
package BodegaCrud.BodegaCrud.service;

import BodegaCrud.BodegaCrud.entities.Usuario;
import BodegaCrud.BodegaCrud.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;


public class UsuarioServiceImplTest {
    
    @Mock
    private UsuarioRepository usuarioRepository;
    
    @InjectMocks
    private UsuarioServiceImpl usuarioServiceImpl;
    
    private Usuario usuario;
    private List<Usuario> listaUsuario;
    
    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        
        listaUsuario = new ArrayList<>();
        usuario = new Usuario();
        
        
        usuario.setId(1L);
        usuario.setNombre("Camiseta Adidas");
        usuario.setEmail("test@test.com");
        usuario.setUsername("test");
        usuario.setPassword("12345");
        
        listaUsuario.add(usuario);
    }
    
    @Test
    void save(){
        when(usuarioRepository.findAll()).thenReturn(listaUsuario);
        assertNotNull(usuarioServiceImpl.listUsuarios());
    }
}
