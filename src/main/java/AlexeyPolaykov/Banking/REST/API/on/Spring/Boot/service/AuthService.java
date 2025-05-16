package AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.service;

import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.dto.request.LoginRequest;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse authenticate(LoginRequest request);
}