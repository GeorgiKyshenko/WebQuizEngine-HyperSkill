package engine.services;

import engine.models.User;

public interface UserService {
    void saveUser(User user) throws IllegalAccessException;
}
