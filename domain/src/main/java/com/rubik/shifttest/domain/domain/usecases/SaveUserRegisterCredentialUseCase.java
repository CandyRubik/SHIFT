package com.rubik.shifttest.domain.domain.usecases;

import com.rubik.shifttest.domain.domain.models.UserRegisterCredential;
import com.rubik.shifttest.domain.domain.repository.UserRepository;

public class SaveUserRegisterCredentialUseCase {

    private UserRepository repository;

    public SaveUserRegisterCredentialUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public boolean execute(UserRegisterCredential userRegisterCredential) {
        return repository.saveUserRegisterCredential(userRegisterCredential);
    }
}
