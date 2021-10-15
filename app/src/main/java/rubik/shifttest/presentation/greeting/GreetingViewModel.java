package rubik.shifttest.presentation.greeting;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rubik.shifttest.domain.domain.models.UserRegisterCredential;
import com.rubik.shifttest.domain.domain.usecases.GetUserRegisterCredentialUseCase;

public class GreetingViewModel extends ViewModel {

    private GetUserRegisterCredentialUseCase getUserRegisterCredentialUseCase;
    private MutableLiveData<UserRegisterCredential> userRegisterCredentialLive;

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
