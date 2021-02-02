package app.web.appgateway.user.domain.dto;

import app.web.appgateway.user.domain.enums.Role;

import java.util.List;

public class AccountDataInDTO {
    private final String login;
    private final String password;
    private final Role role;

    public AccountDataInDTO(String login, String password, Role role) {
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
