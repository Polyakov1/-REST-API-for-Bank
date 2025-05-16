package AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Результат перевода")
public record TransferResponse(
        @Schema(description = "ID транзакции", example = "12345")
        Long transactionId,

        @Schema(description = "ID отправителя", example = "1")
        Long fromUserId,

        @Schema(description = "ID получателя", example = "2")
        Long toUserId,

        @Schema(description = "Сумма перевода", example = "500.00")
        BigDecimal amount,

        @Schema(description = "Новый баланс отправителя", example = "1000.75")
        BigDecimal senderNewBalance,

        @Schema(description = "Новый баланс получателя", example = "2500.25")
        BigDecimal receiverNewBalance,

        @Schema(description = "Дата и время перевода", example = "2023-05-20T14:30:45")
        LocalDateTime timestamp
) {}