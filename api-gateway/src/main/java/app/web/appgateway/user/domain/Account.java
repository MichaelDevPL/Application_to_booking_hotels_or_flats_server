package app.web.appgateway.user.domain;

import app.web.appgateway.user.domain.dto.AccountInDTO;
import app.web.appgateway.user.domain.enums.Role;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "question_to_remind_password")
    private String questionToRemindPassword;

    @Column(name = "answer_to_remind_password")
    private String answerToRemindPassword;

    @OneToOne(
            mappedBy = "account",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private User user;

    public Account() {
    }

    public Account(AccountInDTO accountInDTO) {
        this.login = accountInDTO.getLogin();
        this.password = accountInDTO.getPassword();
        this.role = accountInDTO.getRole();
        this.questionToRemindPassword = accountInDTO.getQuestionToRemindPassword();
        this.answerToRemindPassword = accountInDTO.getAnswerToRemindPassword();
    }

    public Account(String login, String password, Role role, String questionToRemindPassword, String answerToRemindPassword) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.questionToRemindPassword = questionToRemindPassword;
        this.answerToRemindPassword = answerToRemindPassword;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getQuestionToRemindPassword() {
        return questionToRemindPassword;
    }

    public void setQuestionToRemindPassword(String questionToRemindPassword) {
        this.questionToRemindPassword = questionToRemindPassword;
    }

    public String getAnswerToRemindPassword() {
        return answerToRemindPassword;
    }

    public void setAnswerToRemindPassword(String answerToRemindPassword) {
        this.answerToRemindPassword = answerToRemindPassword;
    }
}
