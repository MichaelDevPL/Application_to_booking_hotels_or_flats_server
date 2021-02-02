package app.web.appgateway.user.domain.dto;

import app.web.appgateway.user.domain.Account;
import app.web.appgateway.util.DtoAssembler;
import org.springframework.stereotype.Service;

@Service
public class AccountOutDtoAssembler implements DtoAssembler <AccountDataOutDTO, Account> {

    @Override
    public AccountDataOutDTO assemble(Account entityObject) {
        return new AccountDataOutDTO(entityObject.getId(), entityObject.getLogin(), entityObject.getRole());
    }
}
