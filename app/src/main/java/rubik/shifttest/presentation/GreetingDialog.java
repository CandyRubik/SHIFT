package rubik.shifttest.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class GreetingDialog extends AppCompatDialogFragment {

    private String mGreeting;
    private static final String GREET = "Hello";

    GreetingDialog(String greeting) {
        mGreeting = greeting;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder.setTitle(GREET).setMessage(mGreeting).create();
    }

}
