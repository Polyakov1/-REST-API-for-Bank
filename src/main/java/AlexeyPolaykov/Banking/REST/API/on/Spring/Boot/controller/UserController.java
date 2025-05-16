package AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.controller;



import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.dto.request.UserUpdateRequest;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.dto.response.UserResponse;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Users", description = "API для работы с пользователями")
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "Поиск пользователей", description = "Фильтрация по различным параметрам")
    public ResponseEntity<Page<UserResponse>> searchUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) LocalDate dateOfBirth,
            @PageableDefault Pageable pageable) {

        return ResponseEntity.ok(userService.searchUsers(name, email, phone, dateOfBirth, pageable));
    }

    @PutMapping("/{userId}/emails")
    @Operation(summary = "Обновление email", description = "Добавление/изменение email пользователя")
    public ResponseEntity<UserResponse> updateEmails(
            @PathVariable Long userId,
            @Valid @RequestBody UserUpdateRequest request,
            @AuthenticationPrincipal UserDetails currentUser) {

        return ResponseEntity.ok(userService.updateEmails(userId, request, currentUser));
    }

    @PutMapping("/{userId}/phones")
    @Operation(summary = "Обновление телефона", description = "Добавление/изменение телефона пользователя")
    public ResponseEntity<UserResponse> updatePhones(
            @PathVariable Long userId,
            @Valid @RequestBody UserUpdateRequest request,
            @AuthenticationPrincipal UserDetails currentUser) {

        return ResponseEntity.ok(userService.updatePhones(userId, request, currentUser));
    }
}