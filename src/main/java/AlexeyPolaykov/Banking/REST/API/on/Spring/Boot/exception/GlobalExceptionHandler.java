package AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.exception;

import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.dto.response.ApiResponse;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.dto.response.ValidationErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<Void>> handleApiException(ApiException ex) {
        return ResponseEntity.status(ex.getHttpStatus())
                .body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(
            MethodArgumentNotValidException ex) {

        List<ValidationErrorResponse.Violation> violations = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::mapToViolation)
                .collect(Collectors.toList());

        return ResponseEntity.badRequest()
                .body(ApiResponse.validationError(new ValidationErrorResponse(violations)));
    }

    private ValidationErrorResponse.Violation mapToViolation(FieldError fieldError) {
        return new ValidationErrorResponse.Violation(
                fieldError.getField(),
                fieldError.getDefaultMessage());
    }
}