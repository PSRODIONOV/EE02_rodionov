package be.business;


import be.entity.User;

public interface UserBusinessService {

    User login(String user, String password);
    void registration(String user, String password, String address);
    void logout();
}
