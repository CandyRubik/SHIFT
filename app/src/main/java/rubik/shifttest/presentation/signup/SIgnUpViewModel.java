package rubik.shifttest.presentation.signup;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rubik.shifttest.domain.domain.models.UserRegisterCredential;
import com.rubik.shifttest.domain.domain.usecases.SaveUserRegisterCredentialUseCase;
import com.rubik.shifttest.domain.domain.usecases.ValidateDateUseCase;
import com.rubik.shifttest.domain.domain.usecases.ValidateEqualsPasswordsUseCase;
import com.rubik.shifttest.domain.domain.usecases.ValidateFirstNameUseCase;
import com.rubik.shifttest.domain.domain.usecases.ValidateLastNameUseCase;

public class SIgnUpViewModel extends ViewModel {
    private ValidateFirstNameUseCase validateFirstNameUseCase;
    private ValidateLastNameUseCase validateLastNameUseCase;
    private ValidateDateUseCase validateDateUseCase;
    private ValidateEqualsPasswordsUseCase validateEqualsPasswordsUseCase;
    private SaveUserRegisterCredentialUseCase saveUserRegisterCredentialUseCase;

    private MutableLiveData<Boolean> isFirstNameCorrect;
    private MutableLiveData<Boolean>  isLastNameCorrect;
    private MutableLiveData<Boolean>  isPasswordsEquals;
    private MutableLiveData<Boolean>  isDateCorrect;


    public SIgnUpViewModel(ValidateFirstNameUseCase validateFirstNameUseCase,
                           ValidateLastNameUseCase validateLastNameUseCase,
                           ValidateDateUseCase validateDateUseCase,
                           ValidateEqualsPasswordsUseCase validateEqualsPasswordsUseCase,
                           SaveUserRegisterCredentialUseCase saveUserRegisterCredentialUseCase) {
        this.validateFirstNameUseCase = validateFirstNameUseCase;
        this.validateLastNameUseCase = validateLastNameUseCase;
        this.validateDateUseCase = validateDateUseCase;
        this.validateEqualsPasswordsUseCase = validateEqualsPasswordsUseCase;
        this.saveUserRegisterCredentialUseCase = saveUserRegisterCredentialUseCase;

        isFirstNameCorrect = new MutableLiveData<>(false);
        isLastNameCorrect = new MutableLiveData<>(false);
        isPasswordsEquals = new MutableLiveData<>(false);
        isDateCorrect = new MutableLiveData<>(false);
    }

    public void saveUserRegisterCredentialUseCase(UserRegisterCredential userRegisterCredential) {
        saveUserRegisterCredentialUseCase.execute(userRegisterCredential);
    }

    public void validateFirstNameUseCase(CharSequence charSequence) {
        if(validateFirstNameUseCase.execute(charSequence)) {
            isFirstNameCorrect.setValue(true);
        } else {
            isFirstNameCorrect.setValue(false);
        }
    }

    public void validateLastNameUseCase(CharSequence charSequence) {
        if(validateLastNameUseCase.execute(charSequence)) {
            isLastNameCorrect.setValue(true);
        } else {
            isLastNameCorrect.setValue(false);
        }
    }

    public void validateDateUseCase(CharSequence charSequence) {
        if(validateDateUseCase.execute(charSequence)) {
            isDateCorrect.setValue(true);
        } else {
            isDateCorrect.setValue(false);
        }
    }

    public void validateEqualsPasswordsUseCase(CharSequence first, CharSequence second) {
        if(validateEqualsPasswordsUseCase.execute(first, second)) {
            isPasswordsEquals.setValue(true);
        } else {
            isPasswordsEquals.setValue(false);
        }
    }

    public LiveData<Boolean> getIsFirstNameCorrect() {
        return isFirstNameCorrect;
    }

    public LiveData<Boolean> getIsLastNameCorrect() {
        return isLastNameCorrect;
    }

    public LiveData<Boolean> getIsPasswordsEquals() {
        return isPasswordsEquals;
    }

    public LiveData<Boolean> getIsDateCorrect() {
        return isDateCorrect;
    }
}
