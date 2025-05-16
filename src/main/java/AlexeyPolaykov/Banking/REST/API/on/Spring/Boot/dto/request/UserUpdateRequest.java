package AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserUpdateRequest(
        @Schema(description = "Новое значение (email или телефон)", example = "new@email.com или 79207654321")
        @NotBlank(message = "Значение не может быть пустым")

        // Для email
        @Email(message = "Некорректный формат email", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
        @Size(max = 200, message = "Email не должен превышать 200 символов")

        // Для телефона
        @Pattern(regexp = "^7\\d{10}$", message = "Телефон должен быть в формате 79201234567")
        String value
) {}