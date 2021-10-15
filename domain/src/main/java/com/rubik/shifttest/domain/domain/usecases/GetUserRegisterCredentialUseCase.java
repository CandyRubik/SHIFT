package com.rubik.shifttest.domain.domain.usecases;

import com.rubik.shifttest.domain.domain.models.UserRegisterCredential;
import com.rubik.shifttest.domain.domain.repository.UserRepository;

public class GetUserRegisterCredentialUseCase {
    private UserRepository repository;

    public GetUserRegisterCredentialUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public UserRegisterCredential execute() {
        return repository.getUserRegisterCredential();
    }
}
