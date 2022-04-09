package rubik.shifttest.presentation.greeting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.rubik.shifttest.domain.models.UserRegisterCredential;

import rubik.shifttest.R;

public class GreetingFragment extends Fragment {

    private static final String DIALOG_TAG = "dialog";
    private UserRegisterCredential mUserRegisterCredential;
    private GreetingViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_greeting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity(),
                    new GreetingViewModelFactory(requireActivity()))
                    .get(GreetingViewModel.class);

        Button button = view.findViewById(R.id.open_dialog);
        button.setOnClickListener(buttonView -> openDialog());

        viewModel.getUserRegisterCredentialLive().observe(requireActivity(),
                userRegisterCredential -> mUserRegisterCredential = userRegisterCredential);
    }

    private void openDialog() {
        viewModel.getUserRegisterCredential();
        GreetingDialog greetingDialog = new GreetingDialog(mUserRegisterCredential.getFirstName());
        greetingDialog.show(getParentFragmentManager(), DIALOG_TAG);
    }
}