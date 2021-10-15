package com.rubik.shifttest.domain.domain.models;

import java.io.Serializable;

public class UserRegisterCredential implements Serializable {
    private String firstName;
    private String lastName;
    private String bornDate;
    private String password;

    public UserRegisterCredential(String firstName, String lastName, String bornDate, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bornDate = bornDate;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBornDate() {
        return bornDate;
    }

    public String getPassword() {
        return password;
    }

}
