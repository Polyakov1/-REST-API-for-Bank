package AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @Schema(description = "Email или телефон пользователя", example = "user@example.com или 79201234567")
        @NotBlank(message = "Логин не может быть пустым")
        String login,

        @Schema(description = "Пароль", example = "mySecurePassword123")
        @NotBlank(message = "Пароль не может быть пустым")
        @Size(min = 8, max = 500, message = "Пароль должен быть от 8 до 500 символов")
        String password
) {}