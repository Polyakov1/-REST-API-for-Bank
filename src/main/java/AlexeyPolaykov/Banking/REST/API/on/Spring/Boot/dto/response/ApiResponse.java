package AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.dto.response;

import lombok.Builder;

@Builder
public record ApiResponse<T>(
        boolean success,
        T data,
        String error,
        ValidationErrorResponse validationErrors
) {
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null, null);
    }

    public static ApiResponse<Void> error(String message) {
        return new ApiResponse<>(false, null, message, null);
    }

    public static ApiResponse<Void> validationError(ValidationErrorResponse validationErrors) {
        return new ApiResponse<>(false, null, "Validation failed", validationErrors);
    }
}