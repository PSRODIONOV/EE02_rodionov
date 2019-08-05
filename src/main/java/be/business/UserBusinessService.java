package be.business;

import be.entity.User;
import be.utils.ServiceException;

import java.math.BigDecimal;

public interface UserBusinessService{

    User login(String user, String password) throws ServiceException;
    void registration(String user, String password, String address) throws ServiceException;
    User getUserById(Long id) throws ServiceException;
    User getUserByLogin(String login) throws ServiceException;
    void pay(Long id, BigDecimal priceOrder) throws ServiceException;
    Boolean checkLogin(String login);
    void updateDiscount(Long idUser, Integer newDiscount) throws ServiceException;
}
