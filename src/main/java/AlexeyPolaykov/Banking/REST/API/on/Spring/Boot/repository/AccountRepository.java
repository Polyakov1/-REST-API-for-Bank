package AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.repository;

import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import jakarta.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Account> findById(Long id);

    Optional<Account> findByUserId(Long userId);
}