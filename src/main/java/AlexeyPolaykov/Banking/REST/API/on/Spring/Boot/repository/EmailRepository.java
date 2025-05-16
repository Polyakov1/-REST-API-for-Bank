package AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.repository;

import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.model.EmailData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailRepository extends JpaRepository<EmailData, Long> {
    boolean existsByEmail(String email);
    Optional<EmailData> findByEmail(String email);
}