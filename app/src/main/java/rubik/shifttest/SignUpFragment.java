package rubik.shifttest;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

public class SignUpFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button signUpButton = view.findViewById(R.id.sign_up_button);
        
        signUpButton.setOnClickListener(buttonView -> {
            EditText editText = view.findViewById(R.id.edit_text);
            String text = editText.getText().toString();
            GreetingFragment greetingFragment = new GreetingFragment();
            Bundle args = new Bundle();
            args.putString("Message", text);
            greetingFragment.setArguments(args);

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, greetingFragment, "greetingFragment")
                    .addToBackStack(null)
                    .commit();
        });
    }
}