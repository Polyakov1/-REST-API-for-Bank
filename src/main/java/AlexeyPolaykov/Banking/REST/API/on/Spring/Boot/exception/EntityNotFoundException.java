package AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends ApiException {
    public EntityNotFoundException(String entityName, Long id) {
        super(String.format("%s with id %d not found", entityName, id),
                HttpStatus.NOT_FOUND);
    }
}