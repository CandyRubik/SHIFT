package rubik.shifttest.domain.usecases;

import android.widget.EditText;

import rubik.shifttest.domain.models.UserRegisterCredential;
import rubik.shifttest.domain.repository.UserRepository;

public class GetUserRegisterCredentialUseCase {
    private UserRepository mRepository;

    public GetUserRegisterCredentialUseCase(UserRepository repository) {
        mRepository = repository;
    }

    public UserRegisterCredential execute() {
        return mRepository.getUserRegisterCredential();
    }
}
