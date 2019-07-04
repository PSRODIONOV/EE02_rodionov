package be.business;


public interface UserBusinessService {

    boolean login(String user, String password);
    boolean registration(String user, String password, String address);
    void logout();
}
