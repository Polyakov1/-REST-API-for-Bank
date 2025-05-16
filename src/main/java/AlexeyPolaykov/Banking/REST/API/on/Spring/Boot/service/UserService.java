package AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.service;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.dto.request.UserUpdateRequest;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.dto.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface UserService {
    Page<UserResponse> searchUsers(String name, String email, String phone,
                                   LocalDate dateOfBirth, Pageable pageable);

    UserResponse updateEmails(Long userId, UserUpdateRequest request,
                              String currentUserEmail);

    UserResponse updatePhones(Long userId, UserUpdateRequest request,
                              String currentUserEmail);
}