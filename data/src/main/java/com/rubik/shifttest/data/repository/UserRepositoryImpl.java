package com.rubik.shifttest.data.data.repository;

import com.rubik.shifttest.data.data.storage.models.User;
import com.rubik.shifttest.data.data.storage.UserStorage;
import com.rubik.shifttest.domain.domain.models.UserRegisterCredential;
import com.rubik.shifttest.domain.domain.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {

    private UserStorage userStorage;

    public UserRepositoryImpl(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public boolean saveUserRegisterCredential(UserRegisterCredential userRegisterCredential) {
        return userStorage.saveUser(mapToData(userRegisterCredential));
    }

    /**
     * @return null if shared preferences with $KEY_USER_REGISTER_CREDENTIAL is not exist
     *          or an UserRegisterCredential object
     */
    @Override
    public UserRegisterCredential getUserRegisterCredential() {
        return mapToDomain(userStorage.getUser());
    }

    private UserRegisterCredential mapToDomain(User user) {
        return new UserRegisterCredential(user.getFirstName(), user.getLastName(), user.getBornDate(), user.getPassword());
    }

    private User mapToData(UserRegisterCredential userRegisterCredential) {
        return new User(userRegisterCredential.getFirstName(),
                userRegisterCredential.getLastName(), userRegisterCredential.getBornDate(), userRegisterCredential.getPassword());
    }
}
