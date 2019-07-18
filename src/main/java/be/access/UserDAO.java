package be.access;

import be.entity.User;

public interface UserDAO {

    User getUserByLogin(String login);
    User getUserById(Long id);
    void registrationUser(User newUser);
    void setBalance(Long id, Double balance);
}
