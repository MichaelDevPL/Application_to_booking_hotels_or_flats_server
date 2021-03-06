package app.web.appgateway.user.infrastracture.persistance;

import app.web.appgateway.user.domain.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> getUserById(long userId);

    User getUserByAccountId (long accountId);

    Optional<User> getUserByEmail(String email);

    boolean userExistsByEmail(String email);

    void createUser(User user);

    void updateUser(User user);
}
