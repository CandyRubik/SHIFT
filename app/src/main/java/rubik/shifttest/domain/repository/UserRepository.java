package rubik.shifttest.domain.repository;

import rubik.shifttest.domain.models.UserRegisterCredential;

public interface UserRepository {
    boolean saveUserRegisterCredential(UserRegisterCredential userRegisterCredential);
    UserRegisterCredential getUserRegisterCredential();
}
