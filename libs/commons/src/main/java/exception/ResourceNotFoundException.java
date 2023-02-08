package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Resource not found")
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(final String message){
        super(message);
    }

    public ResourceNotFoundException(final String message, final Object... params){
        super(String.format(message, params));
    }

    public ResourceNotFoundException(){
        super("Resource not found");
    }

    public ResourceNotFoundException(final String name, final int id){
        this("%s id=%d not found", name, id);
    }

    public ResourceNotFoundException(final String name, final long id){
        this("%s id=%d not found", name, id);
    }

    public ResourceNotFoundException(final String name, final String condition){
        this("%s not found by [%s]", name, condition);
    }

}
