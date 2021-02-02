package app.web.appgateway.user.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserAccountDataInWrapper {

    @JsonProperty("accountData")
    private final AccountDataInDTO accountData;
    @JsonProperty("userData")
    private final UserDataOutAndInDTO userData;

    public UserAccountDataInWrapper(AccountDataInDTO accountData, UserDataOutAndInDTO userData) {
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
