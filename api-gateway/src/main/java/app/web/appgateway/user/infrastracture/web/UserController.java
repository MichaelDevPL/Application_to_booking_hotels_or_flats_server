package app.web.appgateway.user.infrastracture.web;

import app.web.appgateway.user.domain.User;
import app.web.appgateway.user.infrastracture.persistance.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/upload")
    public void uploadUserData (@RequestBody User user) {
        this.userRepository.updateUser(user);
    }
}
