package AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.repository;

import AlexeyPolaykov.Banking.REST.API.on.Spring.Boot.model.PhoneData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneData, Long> {
    boolean existsByPhone(String phone);
    Optional<PhoneData> findByPhone(String phone);
}