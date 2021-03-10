package app.web.appgateway.user.domain.dto;

import app.web.appgateway.user.domain.Account;
import app.web.appgateway.util.DtoAssembler;
import org.springframework.stereotype.Service;

@Service
public class AccountOutAssembler implements DtoAssembler <AccountOutDTO, Account> {

    @Override
    public AccountOutDTO assemble(Account entityObject) {
        return new AccountOutDTO(entityObject.getId(), entityObject.getLogin(), entityObject.getRole());
    }
}
