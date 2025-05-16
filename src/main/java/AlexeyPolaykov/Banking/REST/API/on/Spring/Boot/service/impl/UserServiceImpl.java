package AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.service.impl;

import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.dto.request.UserUpdateRequest;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.dto.response.UserResponse;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.exception.*;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.model.*;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.repository.EmailRepository;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.repository.PhoneRepository;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.repository.UserRepository;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EmailRepository emailRepository;
    private final PhoneRepository phoneRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<UserResponse> searchUsers(String name, String email, String phone,
                                          LocalDate dateOfBirth, Pageable pageable) {
        return userRepository.searchUsers(name, email, phone, dateOfBirth, pageable)
                .map(this::mapToUserResponse);
    }

    @Override
    @Transactional
    public UserResponse updateEmails(Long userId, UserUpdateRequest request,
                                     String currentUserEmail) {
        User user = getUserWithPermissionCheck(userId, currentUserEmail);

        if (emailRepository.existsByEmail(request.value())) {
            throw new DuplicateDataException("Email", request.value());
        }

        EmailData newEmail = EmailData.builder()
                .email(request.value())
                .isPrimary(false)
                .build();

        user.addEmail(newEmail);
        userRepository.save(user);

        return mapToUserResponse(user);
    }

    @Override
    @Transactional
    public UserResponse updatePhones(Long userId, UserUpdateRequest request,
                                     String currentUserEmail) {
        User user = getUserWithPermissionCheck(userId, currentUserEmail);

        if (phoneRepository.existsByPhone(request.value())) {
            throw new DuplicateDataException("Phone", request.value());
        }

        PhoneData newPhone = PhoneData.builder()
                .phone(request.value())
                .isPrimary(false)
                .build();

        user.addPhone(newPhone);
        userRepository.save(user);

        return mapToUserResponse(user);
    }

    private User getUserWithPermissionCheck(Long userId, String currentUserEmail) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User", userId));

        boolean isCurrentUser = user.getEmails().stream()
                .anyMatch(email -> email.getEmail().equals(currentUserEmail));

        if (!isCurrentUser) {
            throw new OperationNotAllowedException("Update another user's data");
        }

        return user;
    }

    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .dateOfBirth(user.getDateOfBirth())
                .emails(user.getEmails().stream()
                        .map(EmailData::getEmail)
                        .collect(Collectors.toList()))
                .phones(user.getPhones().stream()
                        .map(PhoneData::getPhone)
                        .collect(Collectors.toList()))
                .balance(user.getAccount().getBalance().doubleValue())
                .build();
    }
}