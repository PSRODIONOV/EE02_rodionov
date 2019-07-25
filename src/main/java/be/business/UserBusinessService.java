package be.business;

import be.entity.User;
import be.utils.ServiceException;

import java.math.BigDecimal;

public interface UserBusinessService{

    User login(String user, String password);
    void registration(String user, String password, String address) throws ServiceException;
    User getUserById(Long id);
    void pay(Long id, BigDecimal priceOrder) throws ServiceException;
}
