package AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.dto.request;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springdoc.api.annotations.ParameterObject;
import java.time.LocalDate;

@ParameterObject
@Schema(description = "Параметры фильтрации пользователей")
public record UserFilterRequest(
        @Parameter(description = "Имя пользователя (поиск по началу строки)")
        String name,

        @Parameter(description = "Email (точное совпадение)")
        String email,

        @Parameter(description = "Телефон (точное совпадение)")
        String phone,

        @Parameter(description = "Дата рождения (фильтр по дате после указанной)")
        LocalDate dateOfBirth
) {}