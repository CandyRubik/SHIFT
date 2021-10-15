package rubik.shifttest.data.storage;

import rubik.shifttest.data.storage.models.User;

public interface UserStorage {
    boolean saveUser(User user);
    User getUser();
}
