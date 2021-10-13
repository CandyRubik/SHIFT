package rubik.shifttest.presentation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputLayout;

import rubik.shifttest.R;
import rubik.shifttest.domain.usecases.EqualValidator;
import rubik.shifttest.domain.usecases.GetUserRegisterCredentialUseCase;
import rubik.shifttest.domain.usecases.SaveDateUseCase;
import rubik.shifttest.domain.usecases.ValidateDateUseCase;
import rubik.shifttest.domain.usecases.ValidateEqualsPasswordsUseCase;
import rubik.shifttest.domain.usecases.ValidateFirstNameUseCase;
import rubik.shifttest.domain.usecases.ValidateLastNameUseCase;
import rubik.shifttest.domain.usecases.Validator;

public class SignUpFragment extends Fragment {

    private static final String USER_KEY = "UserObj";
    private static final String GREETING_FRAGMENT_TAG = "greetingFragment";
    private static final String DATE_PICKER_TAG = "datePicker";
    private static final String FIELDS_ERROR_MESSAGE = "Make sure that all fields are not red";

    private static final String DATE_PICKER_TITLE = "Choose a date of birth";
    private static final String FIRST_NAME_INCORRECT = "The first name is incorrect";
    private static final String LAST_NAME_INCORRECT = "The last name is incorrect";
    private static final String DATE_INCORRECT = "The date is empty";
    private static final String PASSWORDS_INCORRECT = "The passwords are not equal";
    private Button mSignUpButton;

    private TextInputLayout mFirstNameInputLayout;
    private EditText mFirstNameEditText;

    private TextInputLayout mLastNameInputLayout;
    private EditText mLastNameEditText;

    private TextInputLayout mDatePickerInputLayout;
    private EditText mDatePickerEditText;

    private EditText mPasswordEditText;

    private TextInputLayout mRePasswordInputLayout;
    private EditText mRePasswordEditText;

    private ValidateFirstNameUseCase mValidateFirstNameUseCase;
    private ValidateLastNameUseCase mValidateLastNameUseCase;
    private ValidateDateUseCase mValidateDateUseCase;
    private SaveDateUseCase mSaveDateUseCase;
    private ValidateEqualsPasswordsUseCase mValidatePasswordsUseCase;


    private boolean mIsFirstNameCorrect = false;
    private boolean mIsLastNameCorrect = false;
    private boolean mIsPasswordsEquals = false;
    private boolean mIsDateCorrect = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSignUpButton = view.findViewById(R.id.sign_up_button);

        setButtonListener();

        mFirstNameInputLayout = view.findViewById(R.id.first_name_text_input_layout);
        mFirstNameEditText = view.findViewById(R.id.first_name_text_input_edit_text);

        mValidateFirstNameUseCase = new ValidateFirstNameUseCase();
        setFirstNameChangeTextListener(mValidateFirstNameUseCase);


        mLastNameInputLayout = view.findViewById(R.id.last_name_text_input_layout);
        mLastNameEditText = view.findViewById(R.id.last_name_text_input_edit_text);

        mValidateLastNameUseCase = new ValidateLastNameUseCase();
        setLastNameChangeTextListener(mValidateLastNameUseCase);


        mDatePickerInputLayout = view.findViewById(R.id.date_picker_input_layout);
        mDatePickerEditText = view.findViewById(R.id.date_picker_input_edit_text);
        mDatePickerEditText.setInputType(InputType.TYPE_NULL);
        mDatePickerEditText.setKeyListener(null);

        MaterialDatePicker materialDatePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText(DATE_PICKER_TITLE).build();
        mDatePickerEditText.setOnFocusChangeListener((view1, hasFocus) -> {
            if(hasFocus)
                materialDatePicker.show(getParentFragmentManager(), DATE_PICKER_TAG);
        });

        mSaveDateUseCase = new SaveDateUseCase(materialDatePicker, mDatePickerEditText);
        mSaveDateUseCase.execute();

        mValidateDateUseCase = new ValidateDateUseCase();
        setDateChangeTextListener(mValidateDateUseCase);

        mPasswordEditText = view.findViewById(R.id.password_input_edit_text);

        mRePasswordInputLayout = view.findViewById(R.id.re_password_text_input_layout);
        mRePasswordEditText = view.findViewById(R.id.re_password_input_edit_text);

        mValidatePasswordsUseCase = new ValidateEqualsPasswordsUseCase();
        setPasswordsEqualsTextListener(mValidatePasswordsUseCase);
    }

    private void setButtonListener () {
        mSignUpButton.setOnClickListener(buttonView -> {
            if(mIsFirstNameCorrect
                && mIsLastNameCorrect
                && mIsDateCorrect
                && mIsPasswordsEquals) {
                    GreetingFragment greetingFragment = new GreetingFragment();
                    Bundle args = new Bundle();
                    GetUserRegisterCredentialUseCase getUserRegisterCredentialUseCase =
                            new GetUserRegisterCredentialUseCase(mFirstNameEditText,
                                    mLastNameEditText, mDatePickerEditText, mRePasswordEditText);

                    args.putSerializable(USER_KEY, getUserRegisterCredentialUseCase.execute());
                    greetingFragment.setArguments(args);

                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, greetingFragment, GREETING_FRAGMENT_TAG)
                            .addToBackStack(null)
                            .commit();
            } else {
                Toast toast = Toast.makeText(getActivity(),
                        FIELDS_ERROR_MESSAGE, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void setFirstNameChangeTextListener(Validator validator) {
        mFirstNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (validator.execute(charSequence)) {
                    mIsFirstNameCorrect = true;
                    mFirstNameInputLayout.setError(null);
                } else {
                    mIsFirstNameCorrect = false;
                    mFirstNameInputLayout.setError(FIRST_NAME_INCORRECT);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setLastNameChangeTextListener(Validator validator) {
        mLastNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (validator.execute(charSequence)) {
                    mIsLastNameCorrect = true;
                    mLastNameInputLayout.setError(null);
                } else {
                    mIsLastNameCorrect = false;
                    mLastNameInputLayout.setError(SignUpFragment.LAST_NAME_INCORRECT);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setDateChangeTextListener(Validator validator) {
        mDatePickerEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (validator.execute(charSequence)) {
                    mIsDateCorrect = true;
                    mDatePickerInputLayout.setError(null);
                } else {
                    mIsDateCorrect = false;
                    mDatePickerInputLayout.setError(SignUpFragment.DATE_INCORRECT);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setPasswordsEqualsTextListener(EqualValidator validator) {
        mRePasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (validator.execute(charSequence, mPasswordEditText.getText())) {
                    mIsPasswordsEquals = true;
                    mRePasswordInputLayout.setError(null);
                } else if (charSequence.toString().isEmpty()) {
                    mRePasswordInputLayout.setError(null);
                    mIsPasswordsEquals = false;
                } else {
                    mIsPasswordsEquals = false;
                    mRePasswordInputLayout.setError(SignUpFragment.PASSWORDS_INCORRECT);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

}