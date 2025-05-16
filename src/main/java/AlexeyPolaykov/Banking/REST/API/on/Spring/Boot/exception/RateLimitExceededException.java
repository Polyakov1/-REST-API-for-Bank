package AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.exception;

import org.springframework.http.HttpStatus;

public class RateLimitExceededException extends ApiException {
    public RateLimitExceededException() {
        super("Rate limit exceeded", HttpStatus.TOO_MANY_REQUESTS);
    }
}