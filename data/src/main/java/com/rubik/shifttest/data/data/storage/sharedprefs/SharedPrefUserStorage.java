package com.rubik.shifttest.data.data.storage.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import com.rubik.shifttest.data.data.storage.UserStorage;
import com.rubik.shifttest.data.data.storage.models.User;

public class SharedPrefUserStorage implements UserStorage {


    public static final String SHARED_PREFS_USER_REGISTER_CREDENTIAL = "user_register_credential";
    public static final String KEY_USER_REGISTER_CREDENTIAL = "key_user_register_credential";
    private final Context context;
    private final SharedPreferences sharedPreferences;

    public SharedPrefUserStorage(Context context) {
        this.context = context;
        sharedPreferences = this.context.getSharedPreferences(SHARED_PREFS_USER_REGISTER_CREDENTIAL, android.content.Context.MODE_PRIVATE);
    }
    @Override
    public boolean saveUser(User user) {
        Gson gson = new Gson();
        sharedPreferences.edit().putString(KEY_USER_REGISTER_CREDENTIAL, gson.toJson(user)).apply();
        return true;
    }

    @Override
    public User getUser() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_USER_REGISTER_CREDENTIAL, null);
        return (json == null) ? null : gson.fromJson(json, User.class);
    }
}
