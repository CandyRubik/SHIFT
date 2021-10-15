package rubik.shifttest.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;

import rubik.shifttest.R;
import com.rubik.shifttest.data.data.storage.sharedprefs.SharedPrefUserStorage;

public class MainActivity extends AppCompatActivity {

    private static final String SIGN_UP_FRAGMENT_TAG = "signUpFragment";
    private static final String GREETING_FRAGMENT_TAG = "greetingFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SharedPreferences sharedPrefs = getSharedPreferences(SharedPrefUserStorage.SHARED_PREFS_USER_REGISTER_CREDENTIAL, MODE_PRIVATE);
        if(sharedPrefs.contains(SharedPrefUserStorage.KEY_USER_REGISTER_CREDENTIAL)) {
            GreetingFragment greetingFragment = new GreetingFragment();
            fragmentTransaction.add(R.id.fragment_container, greetingFragment, GREETING_FRAGMENT_TAG);
            fragmentTransaction.commit();
        } else {
            SignUpFragment signUpFragment = new SignUpFragment();
            fragmentTransaction.add(R.id.fragment_container, signUpFragment, SIGN_UP_FRAGMENT_TAG);
            fragmentTransaction.commit();
        }
    }
}