package AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.exception;

import org.springframework.http.HttpStatus;

public class InsufficientFundsException extends ApiException {
    public InsufficientFundsException(Long accountId) {
        super(String.format("Insufficient funds in account %d", accountId),
                HttpStatus.BAD_REQUEST);
    }
}