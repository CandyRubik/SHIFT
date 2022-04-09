package com.rubik.shifttest.domain.usecases;

import com.rubik.shifttest.domain.models.UserRegisterCredential;
import com.rubik.shifttest.domain.repository.UserRepository;

public class GetUserRegisterCredentialUseCase {
    private final UserRepository repository;

    public GetUserRegisterCredentialUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public UserRegisterCredential execute() {
        return repository.getUserRegisterCredential();
    }
}
