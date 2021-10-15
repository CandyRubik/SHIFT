package rubik.shifttest.domain.usecases;

import rubik.shifttest.domain.models.UserRegisterCredential;
import rubik.shifttest.domain.repository.UserRepository;

public class GetUserRegisterCredentialUseCase {
    private UserRepository repository;

    public GetUserRegisterCredentialUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public UserRegisterCredential execute() {
        return repository.getUserRegisterCredential();
    }
}
