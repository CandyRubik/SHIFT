package rubik.shifttest.presentation.greeting;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.rubik.shifttest.data.data.repository.UserRepositoryImpl;
import com.rubik.shifttest.data.data.storage.UserStorage;
import com.rubik.shifttest.data.data.storage.sharedprefs.SharedPrefUserStorage;
import com.rubik.shifttest.domain.repository.UserRepository;
import com.rubik.shifttest.domain.usecases.GetUserRegisterCredentialUseCase;

public class GreetingViewModelFactory implements ViewModelProvider.Factory {

    private final GetUserRegisterCredentialUseCase getUserRegisterCredentialUseCase;

    public GreetingViewModelFactory(Context context) {
        UserStorage userStorage = new SharedPrefUserStorage(context);
        UserRepository userRepository = new UserRepositoryImpl(userStorage);
        getUserRegisterCredentialUseCase = new GetUserRegisterCredentialUseCase(userRepository);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new GreetingViewModel(getUserRegisterCredentialUseCase);
    }
}
