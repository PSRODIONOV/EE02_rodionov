package be.access;

import be.entity.User;

import java.math.BigDecimal;

public interface UserDAO {

    User getUserByLogin(String login);
    User getUserById(Long id);
    void registrationUser(User newUser);
    void setBalance(Long id, BigDecimal balance);
    void setDiscount(Long id, Integer discount);
}
