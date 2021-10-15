package rubik.shifttest.presentation.greeting;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.rubik.shifttest.data.data.repository.UserRepositoryImpl;
import com.rubik.shifttest.data.data.storage.UserStorage;
import com.rubik.shifttest.data.data.storage.sharedprefs.SharedPrefUserStorage;
import com.rubik.shifttest.domain.domain.repository.UserRepository;
import com.rubik.shifttest.domain.domain.usecases.GetUserRegisterCredentialUseCase;

public class GreetingViewModelFactory implements ViewModelProvider.Factory {

    private UserStorage userStorage;
    private UserRepository userRepository;
    private GetUserRegisterCredentialUseCase getUserRegisterCredentialUseCase;

    public GreetingViewModelFactory(Context context) {
        userStorage = new SharedPrefUserStorage(context);
        userRepository = new UserRepositoryImpl(userStorage);
        getUserRegisterCredentialUseCase = new GetUserRegisterCredentialUseCase(userRepository);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new GreetingViewModel(getUserRegisterCredentialUseCase);
    }
}
