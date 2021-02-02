package app.web.appgateway.user.domain.dto;

import app.web.appgateway.user.domain.User;
import app.web.appgateway.util.DtoAssembler;
import org.springframework.stereotype.Service;

@Service
public class UserOutDtoAssembler implements DtoAssembler<UserDataOutAndInDTO, User> {

    @Override
    public UserDataOutAndInDTO assemble(User entityObject) {
        return new UserDataOutAndInDTO(entityObject.getName(), entityObject.getSurname(),
                entityObject.getPhone(), entityObject.getEmail());
    }
}
