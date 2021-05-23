package app.web.appgateway.user.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SignUpResponseDTO {

    @JsonProperty("emailExist")
    private boolean emailExist;

    @JsonProperty("loginExist")
    private boolean loginExist;

    @JsonProperty("successAccountCreate")
    private boolean successAccountCreate;

    public SignUpResponseDTO(@JsonProperty("emailExist") boolean emailExist,
                             @JsonProperty("loginExist") boolean loginExist,
                             @JsonProperty("successAccountCreate") boolean successAccountCreate) {
        this.emailExist = emailExist;
        this.loginExist = loginExist;
        this.successAccountCreate = successAccountCreate;
    }

    public boolean isEmailExist() {
        return emailExist;
    }

    public void setEmailExist(boolean emailExist) {
        this.emailExist = emailExist;
    }

    public boolean isLoginExist() {
        return loginExist;
    }

    public void setLoginExist(boolean loginExist) {
        this.loginExist = loginExist;
    }

    public boolean isSuccessAccountCreate() {
        return successAccountCreate;
    }

    public void setSuccessAccountCreate(boolean successAccountCreate) {
        this.successAccountCreate = successAccountCreate;
    }
}
