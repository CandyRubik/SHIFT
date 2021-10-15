package rubik.shifttest.domain.usecases;

import rubik.shifttest.domain.models.UserRegisterCredential;
import rubik.shifttest.domain.repository.UserRepository;

public class SaveUserRegisterCredentialUseCase {

    private UserRepository mRepository;

    public SaveUserRegisterCredentialUseCase(UserRepository repository) {
        mRepository = repository;
    }

    public boolean execute(UserRegisterCredential userRegisterCredential) {
        return mRepository.saveUserRegisterCredential(userRegisterCredential);
    }
}
