package app.web.appgateway.security.authentication;

import app.web.appgateway.security.jtw.JwtToken;
import app.web.appgateway.user.domain.Account;
import app.web.appgateway.user.domain.User;
import app.web.appgateway.user.domain.dto.LoginDataInDTO;
import app.web.appgateway.user.domain.dto.UserAccountDataInWrapper;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signin")
    public JwtToken login(@RequestBody LoginDataInDTO loginDataInDTO){
        return authenticationService.signIn(loginDataInDTO);
    }

    @PostMapping("/signup")
    public boolean signup(@RequestBody UserAccountDataInWrapper dataInWrapper){


        Account newAccount = new Account(dataInWrapper.getAccountData().getLogin(),
                dataInWrapper.getAccountData().getPassword(), dataInWrapper.getAccountData().getRoles());

        User newUser = new User(dataInWrapper.getUserData().getName(), dataInWrapper.getUserData().getSurname(),
                dataInWrapper.getUserData().getPhone(), dataInWrapper.getUserData().getEmail());

        return authenticationService.signUp(newAccount, newUser);
    }

    @GetMapping("/refresh")
    public JwtToken refresh(HttpServletRequest req){
        return authenticationService.refresh(req.getRemoteUser());
    }
}
