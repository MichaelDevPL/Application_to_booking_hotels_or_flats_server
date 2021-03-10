package app.web.appgateway.user.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SignUpWrapper {

    @JsonProperty("accountData")
    private final AccountInDTO accountData;
    @JsonProperty("userData")
    private final UserDTO userData;

    @JsonCreator
    public SignUpWrapper(@JsonProperty("accountData") AccountInDTO accountData, @JsonProperty("userData") UserDTO userData) {
        this.accountData = accountData;
        this.userData = userData;
    }

    public AccountInDTO getAccountData() {
        return accountData;
    }

    public UserDTO getUserData() {
        return userData;
    }

}
