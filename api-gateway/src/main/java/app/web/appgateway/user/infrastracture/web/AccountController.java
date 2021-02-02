package app.web.appgateway.user.infrastracture.web;

import app.web.appgateway.user.domain.Account;
import app.web.appgateway.user.domain.dto.AccountDataOutDTO;
import app.web.appgateway.user.infrastracture.persistance.AccountRepository;
import app.web.appgateway.util.DtoAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountRepository accountRepository;
    private final DtoAssembler<AccountDataOutDTO, Account> accountDataOutAssembler;

    @Autowired
    public AccountController(AccountRepository accountRepository, DtoAssembler<AccountDataOutDTO, Account> accountDataOutAssembler) {
        this.accountRepository = accountRepository;
        this.accountDataOutAssembler = accountDataOutAssembler;
    }

    @GetMapping(value = "/getAccountByNick/{login}")
    public AccountDataOutDTO getAccountByNick(@PathVariable String login){
        Optional<Account> account = accountRepository.getByLogin(login);
        return accountDataOutAssembler.assemble(account.get());
    }

}
