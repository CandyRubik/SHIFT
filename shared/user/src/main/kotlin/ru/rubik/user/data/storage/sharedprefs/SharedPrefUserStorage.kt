package ru.rubik.user.data.storage.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import ru.rubik.user.data.storage.UserStorage
import ru.rubik.user.data.storage.models.UserDTO

class SharedPrefUserStorage(context: Context) : UserStorage {

	private val sharedPreferences: SharedPreferences = context.getSharedPreferences(SHARED_PREFS_USER_REGISTER_CREDENTIAL, Context.MODE_PRIVATE)

	override fun saveUser(user: UserDTO) {
		sharedPreferences.edit().putString(KEY_USER_REGISTER_CREDENTIAL, Gson().toJson(user)).apply()
	}

	override fun getUser(): UserDTO {
		val json: String = requireNotNull(sharedPreferences.getString(KEY_USER_REGISTER_CREDENTIAL, null))
		return Gson().fromJson(json, UserDTO::class.java)
	}

	companion object {

		const val SHARED_PREFS_USER_REGISTER_CREDENTIAL = "user_register_credential"
		const val KEY_USER_REGISTER_CREDENTIAL = "key_user_register_credential"
	}

}