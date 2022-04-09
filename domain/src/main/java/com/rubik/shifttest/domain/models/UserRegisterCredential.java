package com.rubik.shifttest.domain.models;

import java.io.Serializable;

public class UserRegisterCredential implements Serializable {
    private final String firstName;
    private final String lastName;
    private final String bornDate;
    private final String password;

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
