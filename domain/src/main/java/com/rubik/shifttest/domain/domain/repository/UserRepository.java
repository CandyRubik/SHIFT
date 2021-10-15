package com.rubik.shifttest.domain.domain.repository;

import com.rubik.shifttest.domain.domain.models.UserRegisterCredential;

public interface UserRepository {
    boolean saveUserRegisterCredential(UserRegisterCredential userRegisterCredential);
    UserRegisterCredential getUserRegisterCredential();
}
