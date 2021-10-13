package rubik.shifttest.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import rubik.shifttest.R;

public class MainActivity extends AppCompatActivity {

    private static final String SIGN_UP_FRAGMENT_TAG = "signUpFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SignUpFragment signUpFragment = new SignUpFragment();
        fragmentTransaction.add(R.id.fragment_container, signUpFragment, SIGN_UP_FRAGMENT_TAG);
        fragmentTransaction.commit();
    }
}