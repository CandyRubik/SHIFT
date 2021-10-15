package rubik.shifttest.data.repository;

import rubik.shifttest.data.storage.models.User;
import rubik.shifttest.data.storage.UserStorage;
import rubik.shifttest.domain.models.UserRegisterCredential;
import rubik.shifttest.domain.repository.UserRepository;

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
