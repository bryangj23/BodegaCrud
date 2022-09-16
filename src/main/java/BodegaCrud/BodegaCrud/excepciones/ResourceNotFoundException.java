
package BodegaCrud.BodegaCrud.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String nameOfResource;
    private String nameOfField;
    private long valueOfField;

    public String getNameOfResource() {
        return nameOfResource;
    }

    public void setNameOfResource(String nameOfResource) {
        this.nameOfResource = nameOfResource;
    }

    public String getNameOfField() {
        return nameOfField;
    }

    public void setNameOfField(String nameOfField) {
        this.nameOfField = nameOfField;
    }

    public long getValueOfField() {
        return valueOfField;
    }

    public void setValueOfField(long valueOfField) {
        this.valueOfField = valueOfField;
    }

    public ResourceNotFoundException(String nameOfResource, String nameOfField, long valueOfField) {
        super(String.format("%s no emcontrado con : %s : '%s'", nameOfResource, nameOfField, valueOfField));
        this.nameOfResource = nameOfResource;
        this.nameOfField = nameOfField;
        this.valueOfField = valueOfField;
    }

}
