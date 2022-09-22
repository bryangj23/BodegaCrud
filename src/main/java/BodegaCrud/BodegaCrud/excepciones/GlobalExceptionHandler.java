
package BodegaCrud.BodegaCrud.excepciones;

import BodegaCrud.BodegaCrud.dto.ErrorDetalles;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetalles> manejarResourceNotFoundException(ResourceNotFoundException exception,
            WebRequest webRequest){
        
        ErrorDetalles errorDetalle = new ErrorDetalles(new Date(), exception.getMessage(), webRequest.getDescription(false)); 
        
        return new ResponseEntity<>(errorDetalle, HttpStatus.NOT_FOUND);
    } 
    
    @ExceptionHandler(BodegaAppException.class)
    public ResponseEntity<ErrorDetalles> manejarBodegaAppException(BodegaAppException bodegaAppException,
            WebRequest webRequest){
        
        ErrorDetalles errorDetalle = new ErrorDetalles(new Date(), bodegaAppException.getMessage(), webRequest.getDescription(false)); 
        
        return new ResponseEntity<>(errorDetalle, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetalles> manejarGlobalException(Exception exception,
            WebRequest webRequest){
        
        ErrorDetalles errorDetalle = new ErrorDetalles(new Date(), exception.getMessage(), webRequest.getDescription(false)); 
        
        return new ResponseEntity<>(errorDetalle, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Metodo para manejar errores de validaciones
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String nombreCampo = ((FieldError) error).getField();
            String mensaje = error.getDefaultMessage();
            
            errores.put(nombreCampo, mensaje);
        });
        
        return  new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }
}
