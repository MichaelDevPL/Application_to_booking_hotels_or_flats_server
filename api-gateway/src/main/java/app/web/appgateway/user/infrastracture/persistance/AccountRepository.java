package app.web.appgateway.user.infrastracture.persistance;

import app.web.appgateway.user.domain.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface AccountRepository extends UserDetailsService {

    Optional<Account> getAccountById(Long accountId);

    Optional<Account> getByLogin(String login);

    boolean verificationLoginAndPassword(String login, String password);

    void createAccount(Account account);

    Account updateAccount(Account account);

    void deleteAccount(Account account);

    boolean existsByLogin(String login);
}
