package AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Ошибка валидации")
public record ValidationErrorResponse(
        @Schema(description = "Список ошибок")
        List<Violation> violations
) {
    public record Violation(
            @Schema(description = "Поле с ошибкой", example = "email")
            String fieldName,

            @Schema(description = "Сообщение об ошибке", example = "Email должен быть валидным")
            String message
    ) {}
}