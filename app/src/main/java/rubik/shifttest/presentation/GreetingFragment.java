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
import rubik.shifttest.domain.models.UserRegisterCredential;

public class GreetingFragment extends Fragment {

    private UserRegisterCredential userRegisterCredential;
    private static final String USER_KEY = "UserObj";
    private static final String DIALOG_TAG = "dialog";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_greeting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();

        if(args != null) {
            userRegisterCredential = (UserRegisterCredential) args.getSerializable(USER_KEY);
        } else {
            userRegisterCredential = null;
        }

        Button button = view.findViewById(R.id.open_dialog);
        button.setOnClickListener(buttonView -> {
            openDialog();
        });
    }

    private void openDialog() {
        GreetingDialog greetingDialog = new GreetingDialog(userRegisterCredential.getFirstName());
        greetingDialog.show(getParentFragmentManager(), DIALOG_TAG);
    }
}