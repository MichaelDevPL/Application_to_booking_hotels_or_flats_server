package app.web.appgateway.security.authentication;

import app.web.appgateway.security.jtw.JwtToken;
import app.web.appgateway.user.domain.Account;
import app.web.appgateway.user.domain.User;
import app.web.appgateway.user.domain.dto.SignInDTO;
import app.web.appgateway.user.domain.dto.SignUpResponseDTO;
import app.web.appgateway.user.domain.dto.SignUpWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public JwtToken login(@RequestBody SignInDTO signInDTO) {
        return authenticationService.signIn(signInDTO);
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDTO> signup(@RequestBody SignUpWrapper dataInWrapper) {
        Account newAccount = new Account(dataInWrapper.getAccountData());
        User newUser = new User(dataInWrapper.getUserData());

        return new ResponseEntity<SignUpResponseDTO>(authenticationService.signUp(newAccount, newUser),
                HttpStatus.OK);
    }

    @GetMapping("/refresh")
    public JwtToken refresh(HttpServletRequest req) {
        return authenticationService.refresh(req.getRemoteUser());
    }
}
