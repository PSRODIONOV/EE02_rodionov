package be.access;

import be.entity.User;

public interface UserDAO {

    User getUserByLogin(String login);
    void registrationUser(User newUser);
}
