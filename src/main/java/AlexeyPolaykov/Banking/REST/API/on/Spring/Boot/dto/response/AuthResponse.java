package AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ответ на аутентификацию")
public record AuthResponse(
        @Schema(description = "JWT токен", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String token,

        @Schema(description = "Тип токена", example = "Bearer")
        String tokenType,

        @Schema(description = "ID пользователя", example = "1")
        Long userId,

        @Schema(description = "Email пользователя", example = "user@example.com")
        String email
) {}