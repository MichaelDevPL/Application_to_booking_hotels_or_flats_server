package app.web.appgateway.user.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginDataInDTO {

    @JsonProperty("login")
    private final String login;
    @JsonProperty("password")
    private final String password;

    @JsonCreator
    public LoginDataInDTO(@JsonProperty("login") String login,
                          @JsonProperty("password") String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
