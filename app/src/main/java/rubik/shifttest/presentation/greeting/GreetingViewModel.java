package rubik.shifttest.presentation.greeting;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rubik.shifttest.domain.models.UserRegisterCredential;
import com.rubik.shifttest.domain.usecases.GetUserRegisterCredentialUseCase;

public class GreetingViewModel extends ViewModel {

    private final GetUserRegisterCredentialUseCase getUserRegisterCredentialUseCase;
    private final MutableLiveData<UserRegisterCredential> userRegisterCredentialLive;

    public GreetingViewModel(GetUserRegisterCredentialUseCase getUserRegisterCredentialUseCase) {
        this.getUserRegisterCredentialUseCase = getUserRegisterCredentialUseCase;
        userRegisterCredentialLive = new MutableLiveData<>();
    }

    public void getUserRegisterCredential() {
        userRegisterCredentialLive.setValue(getUserRegisterCredentialUseCase.execute());
    }

    public LiveData<UserRegisterCredential> getUserRegisterCredentialLive() {
        return userRegisterCredentialLive;
    }
}
