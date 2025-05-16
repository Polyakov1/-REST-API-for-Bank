package AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

@Schema(description = "Данные пользователя")
public record UserResponse(
        @Schema(description = "ID пользователя", example = "1")
        Long id,

        @Schema(description = "Имя пользователя", example = "Иван Иванов")
        String name,

        @Schema(description = "Дата рождения", example = "1990-01-15")
        LocalDate dateOfBirth,

        @Schema(description = "Список email", example = "[\"user@example.com\", \"secondary@mail.ru\"]")
        List<String> emails,

        @Schema(description = "Список телефонов", example = "[\"79201234567\", \"79159876543\"]")
        List<String> phones,

        @Schema(description = "Баланс счета", example = "1500.75")
        Double balance
) {}