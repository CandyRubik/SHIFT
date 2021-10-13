package rubik.shifttest.domain.usecases;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class ValidateDateUseCase implements Validator {

    @Override
    public boolean execute(CharSequence charSequence) {
        return !charSequence.toString().isEmpty();
    }
}
