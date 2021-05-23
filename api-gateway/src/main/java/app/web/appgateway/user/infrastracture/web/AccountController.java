package app.web.appgateway.user.infrastracture.web;

import app.web.appgateway.user.domain.Account;
import app.web.appgateway.user.domain.User;
import app.web.appgateway.user.domain.dto.AccountOutAssembler;
import app.web.appgateway.user.domain.dto.AccountOutDTO;
import app.web.appgateway.user.infrastracture.persistance.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountRepository accountRepository;
    private final AccountOutAssembler accountOutAssembler;

    @Autowired
    public AccountController(AccountRepository accountRepository, AccountOutAssembler accountOutAssembler) {
        this.accountRepository = accountRepository;
        this.accountOutAssembler = accountOutAssembler;
    }

    @GetMapping(value = "/getAccountByNick/{login}")
    public AccountOutDTO getAccountByNick(@PathVariable String login) {
        Optional<Account> account = accountRepository.getByLogin(login);

        return accountOutAssembler.assemble(account.orElseThrow(() -> new IllegalArgumentException("No user with login: " + login)));
    }

    @GetMapping(value = "/getUserData/{accountId}")
    public User getUserDataByAccountId(@PathVariable long accountId) {
        return accountRepository.getAccountById(accountId)
                .map(Account::getUser).orElseThrow(() -> new IllegalArgumentException("No account with ID: " + accountId));
    }

    @DeleteMapping(value = "/delete/{accountId}")
    public void deleteAccount(@PathVariable long accountId) {
        accountRepository.deleteAccount(accountId);
    }
}
