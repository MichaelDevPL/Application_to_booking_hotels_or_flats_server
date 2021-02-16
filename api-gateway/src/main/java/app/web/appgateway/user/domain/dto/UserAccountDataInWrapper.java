package app.web.appgateway.user.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserAccountDataInWrapper {

    @JsonProperty("accountData")
    private final AccountDataInDTO accountData;
    @JsonProperty("userData")
    private final UserDataOutAndInDTO userData;

    @JsonCreator
    public UserAccountDataInWrapper(@JsonProperty("accountData") AccountDataInDTO accountData,@JsonProperty("userData") UserDataOutAndInDTO userData) {
        this.accountData = accountData;
        this.userData = userData;
    }

    public AccountDataInDTO getAccountData() {
        return accountData;
    }

    public UserDataOutAndInDTO getUserData() {
        return userData;
    }

}
