package app.web.appgateway.user.domain.dto;

import app.web.appgateway.user.domain.enums.Role;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AccountInDTO {

    @JsonProperty("login")
    private final String login;
    @JsonProperty("password")
    private final String password;
    @JsonProperty("role")
    private final Role role;

    @JsonCreator
    public AccountInDTO(@JsonProperty("login") String login,
                        @JsonProperty("password") String password,
                        @JsonProperty("role") Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRoles() {
        return role;
    }
}
