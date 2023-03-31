package engine.services;

import engine.models.User;

import java.util.Optional;

public interface UserService {
    void saveUser(User user);

    Optional<User> findUserByEmail(String email);
}
