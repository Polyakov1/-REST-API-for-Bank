package AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.service.impl;

import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.dto.request.TransferRequest;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.dto.response.TransferResponse;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.exception.*;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.model.Account;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.model.User;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.repository.AccountRepository;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.repository.UserRepository;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public TransferResponse transfer(TransferRequest request, String currentUserEmail) {
        User fromUser = userRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new EntityNotFoundException("User", currentUserEmail));

        if (fromUser.getId().equals(request.toUserId())) {
            throw new OperationNotAllowedException("Transfer to yourself");
        }

        Account fromAccount = fromUser.getAccount();
        Account toAccount = accountRepository.findByUserId(request.toUserId())
                .orElseThrow(() -> new EntityNotFoundException("Recipient account", request.toUserId()));

        validateTransfer(fromAccount, request.amount());

        performTransfer(fromAccount, toAccount, request.amount());

        return TransferResponse.builder()
                .transactionId(System.currentTimeMillis()) // В реальном приложении ID транзакции из БД
                .fromUserId(fromUser.getId())
                .toUserId(request.toUserId())
                .amount(request.amount())
                .senderNewBalance(fromAccount.getBalance())
                .receiverNewBalance(toAccount.getBalance())
                .timestamp(LocalDateTime.now())
                .build();
    }

    private void validateTransfer(Account fromAccount, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("Amount must be positive");
        }

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException(fromAccount.getId());
        }
    }

    private void performTransfer(Account fromAccount, Account toAccount, BigDecimal amount) {
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }
}