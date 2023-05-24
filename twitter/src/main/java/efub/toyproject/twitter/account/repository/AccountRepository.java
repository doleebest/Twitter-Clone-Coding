package efub.toyproject.twitter.account.repository;


import efub.toyproject.twitter.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Boolean existsByUserId(String username);
    Optional<Account> findByUserId(String username);
}
