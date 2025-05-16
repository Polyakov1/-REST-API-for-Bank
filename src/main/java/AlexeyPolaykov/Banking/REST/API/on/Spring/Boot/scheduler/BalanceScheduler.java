package AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.scheduler;

import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor
public class BalanceScheduler {

    private final AccountRepository accountRepository;
    private static final BigDecimal INTEREST_RATE = new BigDecimal("0.10");
    private static final BigDecimal MAX_MULTIPLIER = new BigDecimal("2.07");

    @Scheduled(fixedRate = 30000) // Каждые 30 секунд
    @Transactional
    public void applyInterest() {
        log.info("Starting interest application...");

        accountRepository.findAll().forEach(account -> {
            BigDecimal maxAllowed = account.getInitialDeposit().multiply(MAX_MULTIPLIER);

            if (account.getBalance().compareTo(maxAllowed) < 0) {
                BigDecimal interest = account.getBalance().multiply(INTEREST_RATE)
                        .setScale(2, BigDecimal.ROUND_HALF_UP);

                account.setBalance(account.getBalance().add(interest));
                accountRepository.save(account);

                log.debug("Applied interest {} to account {}", interest, account.getId());
            }
        });

        log.info("Interest application completed");
    }
}