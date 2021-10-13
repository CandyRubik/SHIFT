package rubik.shifttest.domain.usecases;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class ValidateLastNameUseCase implements Validator {
    private static final String LAST_NAME_REGEX = "[a-zA-z]+([ '-][a-zA-Z]+)*";

    @Override
    public boolean execute(CharSequence charSequence) {
        return charSequence.toString().matches(LAST_NAME_REGEX);
    }
}
