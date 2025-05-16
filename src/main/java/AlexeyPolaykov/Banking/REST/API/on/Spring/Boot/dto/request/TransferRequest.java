package AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record TransferRequest(
        @Schema(description = "ID получателя", example = "2")
        @NotNull(message = "ID получателя обязательно")
        Long toUserId,

        @Schema(description = "Сумма перевода", example = "100.50")
        @NotNull(message = "Сумма перевода обязательна")
        @DecimalMin(value = "0.01", message = "Сумма перевода должна быть больше 0")
        BigDecimal amount
) {}