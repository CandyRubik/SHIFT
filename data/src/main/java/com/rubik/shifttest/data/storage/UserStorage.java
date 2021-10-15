package com.rubik.shifttest.data.data.storage;

import com.rubik.shifttest.data.data.storage.models.User;

public interface UserStorage {
    boolean saveUser(User user);
    User getUser();
}
