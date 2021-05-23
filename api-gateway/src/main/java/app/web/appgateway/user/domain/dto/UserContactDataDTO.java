package app.web.appgateway.user.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserContactDataDTO {

    @JsonProperty("name")
    private final String name;

    @JsonProperty("surname")
    private final String surname;

    @JsonProperty("phone")
    private final String phone;

    @JsonProperty("email")
    private final String email;

    public UserContactDataDTO( @JsonProperty("name") String name,
                               @JsonProperty("surname") String surname,
                               @JsonProperty("phone") String phone,
                               @JsonProperty("email")String email) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
