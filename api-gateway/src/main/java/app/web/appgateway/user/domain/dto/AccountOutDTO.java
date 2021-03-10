package app.web.appgateway.user.domain.dto;

import app.web.appgateway.user.domain.User;
import app.web.appgateway.user.domain.enums.Role;

public class AccountOutDTO {
    private final Long id;
    private final String login;
    private final Role roles;

    public AccountOutDTO(Long id, String login, Role roles) {
        this.id = id;
        this.login = login;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public Role getRoles() {
        return roles;
    }
}
