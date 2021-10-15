package rubik.shifttest.presentation.signup;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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
import rubik.shifttest.presentation.greeting.GreetingFragment;

import com.rubik.shifttest.domain.domain.models.UserRegisterCredential;
import com.rubik.shifttest.domain.domain.usecases.EqualValidator;
import com.rubik.shifttest.domain.domain.usecases.Validator;

public class SignUpFragment extends Fragment {

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

    private boolean isFirstNameCorrect;
    private boolean isLastNameCorrect;
    private boolean isPasswordsEquals;
    private boolean isDateCorrect;

    private SIgnUpViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity(),
                new SignUpViewModelFactory(requireActivity()))
                .get(SIgnUpViewModel.class);

        mSignUpButton = view.findViewById(R.id.sign_up_button);
        mSignUpButton.setEnabled(false);

        viewModel.getIsFirstNameCorrect().observe(requireActivity(), aBoolean -> isFirstNameCorrect = aBoolean);

        viewModel.getIsLastNameCorrect().observe(requireActivity(), aBoolean -> isLastNameCorrect = aBoolean);

        viewModel.getIsDateCorrect().observe(requireActivity(), aBoolean -> isDateCorrect = aBoolean);

        viewModel.getIsPasswordsEquals().observe(requireActivity(), aBoolean -> isPasswordsEquals = aBoolean);

        setButtonListener();

        mFirstNameTextInputLayout = view.findViewById(R.id.first_name_text_input_layout);
        mFirstNameEditText = view.findViewById(R.id.first_name_text_input_edit_text);


        setFirstNameChangeTextListener();


        mLastNameTextInputLayout = view.findViewById(R.id.last_name_text_input_layout);
        mLastNameEditText = view.findViewById(R.id.last_name_text_input_edit_text);

        setLastNameChangeTextListener();

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

        setDateChangeTextListener();

        mPasswordEditText = view.findViewById(R.id.password_input_edit_text);

        mRePasswordTextInputLayout = view.findViewById(R.id.re_password_text_input_layout);
        mRePasswordEditText = view.findViewById(R.id.re_password_input_edit_text);

        setPasswordsEqualsTextListener();


    }

    private void setButtonListener () {
        mSignUpButton.setOnClickListener(buttonView -> {
            if(isFirstNameCorrect
                && isLastNameCorrect
                && isPasswordsEquals
                && isDateCorrect) {
                    GreetingFragment greetingFragment = new GreetingFragment();
                    UserRegisterCredential userRegisterCredential = new UserRegisterCredential(mFirstNameEditText.getText().toString(),
                                                                mLastNameEditText.getText().toString(),
                                                                mDatePickerEditText.getText().toString(),
                                                                mRePasswordEditText.getText().toString());
                    viewModel.saveUserRegisterCredentialUseCase(userRegisterCredential);
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

    private void setFirstNameChangeTextListener() {
        mFirstNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModel.validateFirstNameUseCase(charSequence);
                if (isFirstNameCorrect) {
                    mFirstNameTextInputLayout.setError(null);
                } else {
                    mFirstNameTextInputLayout.setError(FIRST_NAME_INCORRECT);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                mSignUpButton.setEnabled(isButtonAvailable());
            }
        });
    }

    private void setLastNameChangeTextListener() {
        mLastNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModel.validateLastNameUseCase(charSequence);
                if (isLastNameCorrect) {
                    mLastNameTextInputLayout.setError(null);
                } else {
                    mLastNameTextInputLayout.setError(SignUpFragment.LAST_NAME_INCORRECT);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                mSignUpButton.setEnabled(isButtonAvailable());
            }
        });
    }

    private void setDateChangeTextListener() {
        mDatePickerEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModel.validateDateUseCase(charSequence);
                if (isDateCorrect) {
                    mDatePickerTextInputLayout.setError(null);
                } else {
                    mDatePickerTextInputLayout.setError(SignUpFragment.DATE_INCORRECT);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                mSignUpButton.setEnabled(isButtonAvailable());
            }
        });
    }

    private void setPasswordsEqualsTextListener() {
        mRePasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModel.validateEqualsPasswordsUseCase(charSequence, mPasswordEditText.getText());
                if (charSequence.toString().isEmpty()) {
                    mRePasswordTextInputLayout.setError(null);
                } else if (isPasswordsEquals) {
                    mRePasswordTextInputLayout.setError(null);
                } else {
                    mRePasswordTextInputLayout.setError(SignUpFragment.PASSWORDS_INCORRECT);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                mSignUpButton.setEnabled(isButtonAvailable());
            }
        });
    }

    private boolean isButtonAvailable() {
        return isFirstNameCorrect
                && isLastNameCorrect
                && isPasswordsEquals
                && isDateCorrect;
    }

}