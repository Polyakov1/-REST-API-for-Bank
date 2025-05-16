package AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.exception;

import org.springframework.http.HttpStatus;

public class OperationNotAllowedException extends ApiException {
    public OperationNotAllowedException(String operation) {
        super(String.format("Operation '%s' not allowed", operation),
                HttpStatus.FORBIDDEN);
    }
}