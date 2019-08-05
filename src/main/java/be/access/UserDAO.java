package be.access;

import be.entity.User;

import java.math.BigDecimal;
import java.util.Optional;

public interface UserDAO {
    Optional<User> getUserByLogin(String login);
    Optional<User> getUserById(Long id);
    void registrationUser(User newUser);
    void setBalance(Long id, BigDecimal balance);
    void setDiscount(Long id, Integer discount);
}
