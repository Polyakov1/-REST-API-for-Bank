package AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.service.impl;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.security.JwtTokenProvider;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.dto.request.LoginRequest;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.dto.response.AuthResponse;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.exception.AuthenticationException;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.model.User;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.repository.UserRepository;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    @Override
    @Transactional(readOnly = true)
    public AuthResponse authenticate(LoginRequest request) {
        User user = userRepository.findByEmailOrPhone(request.login(), request.login())
                .orElseThrow(() -> new AuthenticationException("Invalid login credentials"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new AuthenticationException("Invalid password");
        }

        String token = tokenProvider.generateToken(user);
        return new AuthResponse(token, "Bearer", user.getId(),
                user.getEmails().iterator().next().getEmail());
    }
}