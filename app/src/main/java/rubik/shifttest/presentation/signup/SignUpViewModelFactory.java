package rubik.shifttest.presentation.signup;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.rubik.shifttest.data.data.repository.UserRepositoryImpl;
import com.rubik.shifttest.data.data.storage.UserStorage;
import com.rubik.shifttest.data.data.storage.sharedprefs.SharedPrefUserStorage;
import com.rubik.shifttest.domain.repository.UserRepository;
import com.rubik.shifttest.domain.usecases.SaveUserRegisterCredentialUseCase;
import com.rubik.shifttest.domain.usecases.ValidateDateUseCase;
import com.rubik.shifttest.domain.usecases.ValidateEqualsPasswordsUseCase;
import com.rubik.shifttest.domain.usecases.ValidateFirstNameUseCase;
import com.rubik.shifttest.domain.usecases.ValidateLastNameUseCase;

public class SignUpViewModelFactory implements ViewModelProvider.Factory {

    private final ValidateFirstNameUseCase validateFirstNameUseCase;
    private final ValidateLastNameUseCase validateLastNameUseCase;
    private final ValidateDateUseCase validateDateUseCase;
    private final ValidateEqualsPasswordsUseCase validateEqualsPasswordsUseCase;
    private final SaveUserRegisterCredentialUseCase saveUserRegisterCredentialUseCase;

    public SignUpViewModelFactory(Context context) {
        validateFirstNameUseCase = new ValidateFirstNameUseCase();
        validateLastNameUseCase = new ValidateLastNameUseCase();
        validateDateUseCase = new ValidateDateUseCase();
        validateEqualsPasswordsUseCase = new ValidateEqualsPasswordsUseCase();
        UserStorage userStorage = new SharedPrefUserStorage(context);
        UserRepository userRepository = new UserRepositoryImpl(userStorage);
        saveUserRegisterCredentialUseCase = new SaveUserRegisterCredentialUseCase(userRepository);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SIgnUpViewModel(validateFirstNameUseCase,
                validateLastNameUseCase, validateDateUseCase,
                validateEqualsPasswordsUseCase, saveUserRegisterCredentialUseCase);
    }
}
