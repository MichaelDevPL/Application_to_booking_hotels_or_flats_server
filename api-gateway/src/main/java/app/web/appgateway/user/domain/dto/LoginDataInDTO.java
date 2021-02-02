package app.web.appgateway.user.domain.dto;

public class LoginDataInDTO {
    private final String login;
    private final String password;

    public LoginDataInDTO(String login, String password) {
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
