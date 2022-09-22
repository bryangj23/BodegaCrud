package BodegaCrud.BodegaCrud.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JWTAuthResponseDTO {
    
    private String tokenDeAcceso;    
    private String tipoDeToken = "Bearer";

    public JWTAuthResponseDTO(String tokenDeAcceso, String tipoDeToken) {
        this.tokenDeAcceso = tokenDeAcceso;
        this.tipoDeToken = tipoDeToken;
    }

    public JWTAuthResponseDTO(String tokenDeAcceso) {
        this.tokenDeAcceso = tokenDeAcceso;
    }
}
