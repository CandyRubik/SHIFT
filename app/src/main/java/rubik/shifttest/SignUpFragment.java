package rubik.shifttest;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;
import java.util.regex.Pattern;

import rubik.shifttest.domain.data.UserRegisterCredential;

public class SignUpFragment extends Fragment {

    private View mRootView;

    private TextInputLayout mFirstNameInputLayout;
    private EditText mFirstNameEditText;

    private TextInputLayout mLastNameInputLayout;
    private EditText mLastNameEditText;

    private EditText mDatePickerEditText;

    private TextInputLayout mPasswordInputLayout;
    private EditText mPasswordEditText;

    private TextInputLayout mRePasswordInputLayout;
    private EditText mRePasswordEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRootView = view;

        setButtonListener(view.findViewById(R.id.sign_up_button));

        mFirstNameInputLayout = view.findViewById(R.id.first_name_text_input_layout);
        mFirstNameEditText = view.findViewById(R.id.first_name_text_input_edit_text);

        setFirstNameTextChangedListener();

        mLastNameInputLayout = view.findViewById(R.id.last_name_text_input_layout);
        mLastNameEditText = view.findViewById(R.id.last_name_text_input_edit_text);

        setLastNameTextChangedListener();

        mDatePickerEditText = view.findViewById(R.id.date_picker_input_edit_text);
        mDatePickerEditText.setInputType(InputType.TYPE_NULL);
        mDatePickerEditText.setKeyListener(null);

        setDatePickerListener();

        mPasswordInputLayout = view.findViewById(R.id.password_text_input_layout);
        mPasswordEditText = view.findViewById(R.id.password_input_edit_text);



        mRePasswordInputLayout = view.findViewById(R.id.re_password_text_input_layout);
        mRePasswordEditText = view.findViewById(R.id.re_password_input_edit_text);

        setPasswordEqualsListener();


    }

    private void setButtonListener(Button signUpButton) {
        signUpButton.setOnClickListener(buttonView -> {
            if(mFirstNameInputLayout.getError() == null
                && mLastNameInputLayout.getError() == null
                && mRePasswordInputLayout.getError() == null) {
                UserRegisterCredential userRegisterCredential =
                        new UserRegisterCredential(mFirstNameEditText.getText().toString(), mLastNameEditText.getText().toString(),
                                                    mDatePickerEditText.getText().toString(), mRePasswordEditText.getText().toString());
                GreetingFragment greetingFragment = new GreetingFragment();
                Bundle args = new Bundle();
                args.putSerializable("UserObj", userRegisterCredential);
                greetingFragment.setArguments(args);

                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, greetingFragment, "greetingFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void setFirstNameTextChangedListener() {
        mFirstNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().matches("[A-Z][a-z]*")) {
                    mFirstNameInputLayout.setError("ERROR");
                } else {
                    mFirstNameInputLayout.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setLastNameTextChangedListener() {
        mLastNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().matches("[a-zA-z]+([ '-][a-zA-Z]+)*")) {
                    mLastNameInputLayout.setError("ERROR");
                } else {
                    mLastNameInputLayout.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setDatePickerListener() {

        MaterialDatePicker materialDatePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select born date").build();
        mDatePickerEditText.setOnFocusChangeListener((view1, hasFocus) -> {
            if(hasFocus)
                materialDatePicker.show(getParentFragmentManager(), "Date_picker");
        });

        materialDatePicker.addOnPositiveButtonClickListener(selection -> mDatePickerEditText.setText(materialDatePicker.getHeaderText()));
    }

    private void setPasswordEqualsListener() {
        mRePasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equals(mPasswordEditText.getText().toString())) {
                    mRePasswordInputLayout.setError(null);
                } else {
                    mRePasswordInputLayout.setError("Passwords are not equal");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}