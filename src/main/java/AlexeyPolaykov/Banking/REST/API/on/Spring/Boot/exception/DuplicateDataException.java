package AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.exception;

import org.springframework.http.HttpStatus;

public class DuplicateDataException extends ApiException {
    public DuplicateDataException(String fieldName, String value) {
        super(String.format("%s '%s' already exists", fieldName, value),
                HttpStatus.CONFLICT);
    }
}