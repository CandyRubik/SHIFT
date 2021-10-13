package rubik.shifttest.domain.usecases;

import android.widget.EditText;

import rubik.shifttest.domain.models.UserRegisterCredential;

public class GetUserRegisterCredentialUseCase {
    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    private EditText mDateEditText;
    private EditText mPasswordEditText;

    public GetUserRegisterCredentialUseCase(EditText FirstNameEditText, EditText LastNameEditText, EditText DateEditText, EditText PasswordEditText) {
        mFirstNameEditText = FirstNameEditText;
        mLastNameEditText = LastNameEditText;
        mDateEditText = DateEditText;
        mPasswordEditText = PasswordEditText;
    }

    public UserRegisterCredential execute() {
        return new UserRegisterCredential(mFirstNameEditText.getText().toString(),
                                            mLastNameEditText.getText().toString(), mDateEditText.getText().toString(),
                                            mPasswordEditText.getText().toString());
    }
}
