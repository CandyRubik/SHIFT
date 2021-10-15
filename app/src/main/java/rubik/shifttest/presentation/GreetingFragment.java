package rubik.shifttest.presentation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import rubik.shifttest.R;
import com.rubik.shifttest.data.data.repository.UserRepositoryImpl;
import com.rubik.shifttest.data.data.storage.sharedprefs.SharedPrefUserStorage;
import com.rubik.shifttest.domain.domain.models.UserRegisterCredential;
import com.rubik.shifttest.domain.domain.usecases.GetUserRegisterCredentialUseCase;

public class GreetingFragment extends Fragment {

    private UserRegisterCredential mUserRegisterCredential;
    private SharedPrefUserStorage mSharedPrefUserStorage;
    private UserRepositoryImpl mUserRepositoryImpl;
    private GetUserRegisterCredentialUseCase mGetUserRegisterCredentialUseCase;
    private static final String DIALOG_TAG = "dialog";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_greeting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button = view.findViewById(R.id.open_dialog);
        button.setOnClickListener(buttonView -> {
            openDialog();
        });

        mSharedPrefUserStorage = new SharedPrefUserStorage(requireActivity().getApplicationContext());
        mUserRepositoryImpl = new UserRepositoryImpl(mSharedPrefUserStorage);
        mGetUserRegisterCredentialUseCase = new GetUserRegisterCredentialUseCase(mUserRepositoryImpl);
        mUserRegisterCredential = mGetUserRegisterCredentialUseCase.execute();
    }

    private void openDialog() {
        GreetingDialog greetingDialog = new GreetingDialog(mUserRegisterCredential.getFirstName());
        greetingDialog.show(getParentFragmentManager(), DIALOG_TAG);
    }
}