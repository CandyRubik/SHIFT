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
import com.rubik.shifttest.data.data.repository.UserRepositoryImpl;
import com.rubik.shifttest.data.data.storage.sharedprefs.SharedPrefUserStorage;
import com.rubik.shifttest.domain.domain.models.UserRegisterCredential;
import com.rubik.shifttest.domain.domain.usecases.EqualValidator;
import com.rubik.shifttest.domain.domain.usecases.SaveUserRegisterCredentialUseCase;
import com.rubik.shifttest.domain.domain.usecases.ValidateDateUseCase;
import com.rubik.shifttest.domain.domain.usecases.ValidateEqualsPasswordsUseCase;
import com.rubik.shifttest.domain.domain.usecases.ValidateFirstNameUseCase;
import com.rubik.shifttest.domain.domain.usecases.ValidateLastNameUseCase;
import com.rubik.shifttest.domain.domain.usecases.Validator;

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

    private TextInputLayout mFirstNameTextInputLayout;
    private EditText mFirstNameEditText;

    private TextInputLayout mLastNameTextInputLayout;
    private EditText mLastNameEditText;

    private TextInputLayout mDatePickerTextInputLayout;
    private EditText mDatePickerEditText;

    private EditText mPasswordEditText;

    private TextInputLayout mRePasswordTextInputLayout;
    private EditText mRePasswordEditText;

    private ValidateFirstNameUseCase mValidateFirstNameUseCase;
    private ValidateLastNameUseCase mValidateLastNameUseCase;
    private ValidateDateUseCase mValidateDateUseCase;
    private ValidateEqualsPasswordsUseCase mValidatePasswordsUseCase;

    private UserRepositoryImpl mUserRepositoryImpl;
    private SharedPrefUserStorage mSharedPrefUserStorage;
    private SaveUserRegisterCredentialUseCase mSaveUserRegisterCredentialUseCase;


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
        mSignUpButton.setEnabled(false);
        setButtonListener();

        mFirstNameTextInputLayout = view.findViewById(R.id.first_name_text_input_layout);
        mFirstNameEditText = view.findViewById(R.id.first_name_text_input_edit_text);

        mValidateFirstNameUseCase = new ValidateFirstNameUseCase();
        setFirstNameChangeTextListener(mValidateFirstNameUseCase);


        mLastNameTextInputLayout = view.findViewById(R.id.last_name_text_input_layout);
        mLastNameEditText = view.findViewById(R.id.last_name_text_input_edit_text);

        mValidateLastNameUseCase = new ValidateLastNameUseCase();
        setLastNameChangeTextListener(mValidateLastNameUseCase);


        mDatePickerTextInputLayout = view.findViewById(R.id.date_picker_input_layout);
        mDatePickerEditText = view.findViewById(R.id.date_picker_input_edit_text);
        mDatePickerEditText.setInputType(InputType.TYPE_NULL);
        mDatePickerEditText.setKeyListener(null);

        MaterialDatePicker materialDatePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText(DATE_PICKER_TITLE).build();
        mDatePickerEditText.setOnFocusChangeListener((view1, hasFocus) -> {
            if(hasFocus)
                materialDatePicker.show(getParentFragmentManager(), DATE_PICKER_TAG);
        });

        materialDatePicker.addOnPositiveButtonClickListener(selection -> mDatePickerEditText.setText(materialDatePicker.getHeaderText()));

        mValidateDateUseCase = new ValidateDateUseCase();
        setDateChangeTextListener(mValidateDateUseCase);

        mPasswordEditText = view.findViewById(R.id.password_input_edit_text);

        mRePasswordTextInputLayout = view.findViewById(R.id.re_password_text_input_layout);
        mRePasswordEditText = view.findViewById(R.id.re_password_input_edit_text);

        mValidatePasswordsUseCase = new ValidateEqualsPasswordsUseCase();
        setPasswordsEqualsTextListener(mValidatePasswordsUseCase);

        mSharedPrefUserStorage = new SharedPrefUserStorage(requireActivity().getApplicationContext());
        mUserRepositoryImpl = new UserRepositoryImpl(mSharedPrefUserStorage);
        mSaveUserRegisterCredentialUseCase = new SaveUserRegisterCredentialUseCase(mUserRepositoryImpl);
    }

    private void setButtonListener () {
        mSignUpButton.setOnClickListener(buttonView -> {
            if(mIsFirstNameCorrect
                && mIsLastNameCorrect
                && mIsDateCorrect
                && mIsPasswordsEquals) {
                    GreetingFragment greetingFragment = new GreetingFragment();
                UserRegisterCredential userRegisterCredential = new UserRegisterCredential(mFirstNameEditText.getText().toString(),
                                                                mLastNameEditText.getText().toString(),
                                                                mDatePickerEditText.getText().toString(),
                                                                mRePasswordEditText.getText().toString());

                    mSaveUserRegisterCredentialUseCase.execute(userRegisterCredential);
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
                    mFirstNameTextInputLayout.setError(null);
                } else {
                    mIsFirstNameCorrect = false;
                    mFirstNameTextInputLayout.setError(FIRST_NAME_INCORRECT);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                mSignUpButton.setEnabled(mIsFirstNameCorrect);
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
                    mLastNameTextInputLayout.setError(null);
                } else {
                    mIsLastNameCorrect = false;
                    mLastNameTextInputLayout.setError(SignUpFragment.LAST_NAME_INCORRECT);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                mSignUpButton.setEnabled(mIsLastNameCorrect);
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
                    mDatePickerTextInputLayout.setError(null);
                } else {
                    mIsDateCorrect = false;
                    mDatePickerTextInputLayout.setError(SignUpFragment.DATE_INCORRECT);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                mSignUpButton.setEnabled(mIsDateCorrect);
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
                    mRePasswordTextInputLayout.setError(null);
                } else if (charSequence.toString().isEmpty()) {
                    mRePasswordTextInputLayout.setError(null);
                    mIsPasswordsEquals = false;
                } else {
                    mIsPasswordsEquals = false;
                    mRePasswordTextInputLayout.setError(SignUpFragment.PASSWORDS_INCORRECT);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                mSignUpButton.setEnabled(mIsPasswordsEquals);
            }
        });
    }

}