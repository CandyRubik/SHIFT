package com.rubik.shifttest.domain.usecases;

import com.rubik.shifttest.domain.models.UserRegisterCredential;
import com.rubik.shifttest.domain.repository.UserRepository;

public class SaveUserRegisterCredentialUseCase {

    private final UserRepository repository;

    public SaveUserRegisterCredentialUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public boolean execute(UserRegisterCredential userRegisterCredential) {
        return repository.saveUserRegisterCredential(userRegisterCredential);
    }
}
