package rubik.shifttest.domain.usecases;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class ValidateFirstNameUseCase implements Validator {
    private static final String FIRST_NAME_REGEX = "[A-Z][a-z]*";
    @Override
    public boolean execute(CharSequence charSequence) {
                return charSequence.toString().matches(FIRST_NAME_REGEX);
    }
}
