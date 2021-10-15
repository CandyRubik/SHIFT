package rubik.shifttest.domain.usecases;

import rubik.shifttest.domain.models.UserRegisterCredential;
import rubik.shifttest.domain.repository.UserRepository;

public class SaveUserRegisterCredentialUseCase {

    private UserRepository repository;

    public SaveUserRegisterCredentialUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public boolean execute(UserRegisterCredential userRegisterCredential) {
        return repository.saveUserRegisterCredential(userRegisterCredential);
    }
}
