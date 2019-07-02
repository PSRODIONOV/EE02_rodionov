package be.access;

import be.entity.User;

public interface UserAccessService {

    User getUserByLogin(String login);
    boolean registrationUser(String login, String password, String address);
}
