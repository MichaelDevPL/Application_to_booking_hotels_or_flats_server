package app.web.appgateway.user.domain.dto;

import app.web.appgateway.user.domain.User;
import app.web.appgateway.util.DtoAssembler;
import org.springframework.stereotype.Service;

@Service
public class UserContactDataAssembler implements DtoAssembler<UserContactDataDTO, User> {

    @Override
    public UserContactDataDTO assemble(User entityObject) {
        return new UserContactDataDTO(entityObject.getName(), entityObject.getSurname(),
                entityObject.getPhone(), entityObject.getEmail());
    }
}
