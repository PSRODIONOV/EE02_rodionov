package be.business;

import be.entity.User;
import be.utils.ServiceException;

public interface UserBusinessService{

    User login(String user, String password);
    void registration(String user, String password, String address);
    User getUserById(Long id);
    void pay(Long id, Double priceOrder) throws ServiceException;
}
