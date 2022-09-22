
package BodegaCrud.BodegaCrud.controller;

import BodegaCrud.BodegaCrud.dto.JWTAuthResponseDTO;
import BodegaCrud.BodegaCrud.dto.LoginDTO;
import BodegaCrud.BodegaCrud.dto.RegistroUserDTO;
import BodegaCrud.BodegaCrud.seguridad.JwtTokenProvider;
import BodegaCrud.BodegaCrud.service.UsuarioServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private final AuthenticationManager authenticationManager;
    
    private final JwtTokenProvider jwtTokenProvider;
    
    private final UsuarioServiceImpl usuarioServiceImpl;
    
    public AuthController(AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider,
            UsuarioServiceImpl usuarioServiceImpl){
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider=jwtTokenProvider;
        this.usuarioServiceImpl = usuarioServiceImpl;
    }
    
    @PostMapping("/iniciarSesion")
    public ResponseEntity<JWTAuthResponseDTO> authenticateUser(@RequestBody LoginDTO loginDTO){
        Authentication authentication = 
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUserNameOrEmail(),loginDTO.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        //Obtenemos el token del JwtTokenProvider
        String token = jwtTokenProvider.generarToken(authentication);
        
        return ResponseEntity.ok(new JWTAuthResponseDTO(token));
    }
    
    @PostMapping("/registrarUser")
    public ResponseEntity<String> regstrarUsuario(@RequestBody RegistroUserDTO registroUserDTO){
        
        return new ResponseEntity<>(usuarioServiceImpl.createUsuario(registroUserDTO), HttpStatus.OK);
    }
}
