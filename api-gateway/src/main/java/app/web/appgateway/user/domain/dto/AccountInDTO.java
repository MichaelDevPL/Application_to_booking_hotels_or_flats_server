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

    @JsonProperty("questionToRemindPassword")
    private final String questionToRemindPassword;

    @JsonProperty("answerToRemindPassword")
    private final String answerToRemindPassword;

    @JsonCreator
    public AccountInDTO(@JsonProperty("login") String login,
                        @JsonProperty("password") String password,
                        @JsonProperty("role") Role role,
                        @JsonProperty("questionToRemindPassword") String questionToRemindPassword,
                        @JsonProperty("answerToRemindPassword") String answerToRemindPassword) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.questionToRemindPassword = questionToRemindPassword;
        this.answerToRemindPassword = answerToRemindPassword;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public String getQuestionToRemindPassword() {
        return questionToRemindPassword;
    }

    public String getAnswerToRemindPassword() {
        return answerToRemindPassword;
    }
}
