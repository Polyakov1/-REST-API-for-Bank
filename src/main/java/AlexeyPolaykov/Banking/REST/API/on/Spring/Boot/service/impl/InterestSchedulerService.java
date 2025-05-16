package AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.service.impl;

import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.model.Account;
import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class InterestSchedulerService {

    private final AccountRepository accountRepository;
    private static final BigDecimal INTEREST_RATE = new BigDecimal("0.10");
    private static final BigDecimal MAX_MULTIPLIER = new BigDecimal("2.07");

    @Scheduled(fixedRate = 30000) // Каждые 30 секунд
    @Transactional
    public void applyInterest() {
        accountRepository.findAll().forEach(account -> {
            BigDecimal maxAllowed = account.getInitialDeposit().multiply(MAX_MULTIPLIER);

            if (account.getBalance().compareTo(maxAllowed) < 0) {
                BigDecimal interest = account.getBalance().multiply(INTEREST_RATE)
                        .setScale(2, RoundingMode.HALF_UP);

                account.setBalance(account.getBalance().add(interest));
                accountRepository.save(account);
            }
        });
    }
}