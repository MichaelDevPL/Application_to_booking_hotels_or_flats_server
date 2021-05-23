package app.web.appgateway.security.authentication;

import app.web.appgateway.exception.CustomException;
import app.web.appgateway.security.jtw.JwtToken;
import app.web.appgateway.security.jtw.JwtTokenProvider;
import app.web.appgateway.user.domain.Account;
import app.web.appgateway.user.domain.User;
import app.web.appgateway.user.domain.dto.SignInDTO;
import app.web.appgateway.user.domain.dto.SignUpResponseDTO;
import app.web.appgateway.user.infrastracture.persistance.AccountRepository;
import app.web.appgateway.user.infrastracture.persistance.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(AccountRepository accountRepository, UserRepository userRepository,
                                 PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider,
                                 AuthenticationManager authenticationManager) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    public JwtToken signIn(SignInDTO signInDTO) {
        if (!accountRepository.verificationLoginAndPassword(
                signInDTO.getLogin(), signInDTO.getPassword())) {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    signInDTO.getLogin(), signInDTO.getPassword()));

            //it's ok become condition verificationLoginAndPassword passed
            return jwtTokenProvider.generateJwtToken(
                    signInDTO.getLogin(), accountRepository.getByLogin(
                            signInDTO.getLogin()).get().getRole());
        } else {
            throw new CustomException("Invalid login/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public SignUpResponseDTO signUp(Account account, User user) {

        boolean loginIsAvailable = accountRepository.existsByLogin(account.getLogin());
        boolean emailIsAvailable = userRepository.userExistsByEmail(user.getEmail());
        boolean successAccountCreate = false;

        if (loginIsAvailable && emailIsAvailable) {
            account.setUser(user);
            account.setPassword(passwordEncoder.encode(account.getPassword()));

            user.setAccount(account);
            accountRepository.createAccount(account);
            userRepository.createUser(user);

            successAccountCreate = true;
        }

        return new SignUpResponseDTO(loginIsAvailable, emailIsAvailable, successAccountCreate);
    }

    public JwtToken refresh(String login) {
        if (accountRepository.getByLogin(login).isPresent()) {
            return jwtTokenProvider.generateJwtToken(login, accountRepository.getByLogin(login).get().getRole());
        } else {
            throw new CustomException("Invalid login ", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
