package app.web.appgateway.user.domain.dto;

public class UserDataOutAndInDTO {

    private final String name;
    private final String surname;
    private final String phone;
    private final String email;

    public UserDataOutAndInDTO(String name, String surname, String phoneNumber, String email) {
        this.name = name;
        this.surname = surname;
        this.phone = phoneNumber;
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
