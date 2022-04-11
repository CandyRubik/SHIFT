package rubik.shifttest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import ru.rubik.navigation.NavCommand
import ru.rubik.navigation.NavCommands
import ru.rubik.navigation.NavigationProvider
import ru.rubik.user.data.storage.sharedprefs.SharedPrefUserStorage
import rubik.shifttest.R

class MainActivity : AppCompatActivity(), NavigationProvider {

	private val navController: NavController
		get() = findNavController(R.id.containerView)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}

	override fun onResume() {
		super.onResume()
		val sharedPrefs = getSharedPreferences(SharedPrefUserStorage.SHARED_PREFS_USER_REGISTER_CREDENTIAL, MODE_PRIVATE)
		if (sharedPrefs.contains(SharedPrefUserStorage.KEY_USER_REGISTER_CREDENTIAL)) {
			navController.navigate(R.id.action_registrationFragment_to_greetingFragment)
		}
	}

	override fun launch(navCommand: NavCommand) {
		when (val target = navCommand.target) {
			is NavCommands.DeepLink -> navController.navigate(target.url)
			else                    -> throw IllegalArgumentException("Navigation error while launch")
		}
	}
}