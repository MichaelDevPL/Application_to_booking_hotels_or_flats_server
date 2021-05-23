package app.web.appgateway.user.infrastracture.web;

import app.web.appgateway.user.domain.User;
import app.web.appgateway.user.domain.dto.UserContactDataAssembler;
import app.web.appgateway.user.domain.dto.UserContactDataDTO;
import app.web.appgateway.user.infrastracture.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final UserContactDataAssembler contactDataAssembler;

    @Autowired
    public UserController(UserRepository userRepository, UserContactDataAssembler contactDataAssembler) {
        this.userRepository = userRepository;
        this.contactDataAssembler = contactDataAssembler;
    }

    @PostMapping(value = "/upload")
    public void uploadUserData (@RequestBody User user) {
        this.userRepository.updateUser(user);
    }

    @GetMapping(value = "/contact-data/{accountId}")
    public ResponseEntity<UserContactDataDTO> getUserContactData( @PathVariable long accountId){

        return new ResponseEntity<UserContactDataDTO>(contactDataAssembler.assemble(this.userRepository.getUserByAccountId(accountId))
                , HttpStatus.OK);
    }
}
