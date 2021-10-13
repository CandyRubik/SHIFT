package rubik.shifttest.domain.data;

import java.io.Serializable;

public class UserRegisterCredential implements Serializable {
    private String firstName;
    private String lastName;
    private String BornDate;
    private String PasswordDate;

    public UserRegisterCredential(String firstName, String lastName, String bornDate, String passwordDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        BornDate = bornDate;
        PasswordDate = passwordDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBornDate() {
        return BornDate;
    }

    public String getPasswordDate() {
        return PasswordDate;
    }

}
