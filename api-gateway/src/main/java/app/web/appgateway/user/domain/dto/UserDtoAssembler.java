package app.web.appgateway.user.domain.dto;

import app.web.appgateway.user.domain.User;
import app.web.appgateway.util.DtoAssembler;
import org.springframework.stereotype.Service;

@Service
public class UserDtoAssembler implements DtoAssembler<UserDTO, User> {

    @Override
    public UserDTO assemble(User entityObject) {
        return new UserDTO(entityObject.getName(), entityObject.getSurname(),
                entityObject.getPhone(), entityObject.getEmail());
    }
}
