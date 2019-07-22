package be.business;

import be.access.UserDAO;
import be.entity.User;
import be.utils.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {

    @Autowired
    private UserDAO userDAO;

    private static final Logger LOG = LoggerFactory.getLogger(UserBusinessServiceImpl.class);

    public UserBusinessServiceImpl(){

        LOG.info(":::::::::" + this.getClass() + " IS CREATED:::::::::");
    }

    @Override
    public User login(String login, String password) {
        User user;
        if((user = userDAO.getUserByLogin(login))!= null) {
            if(user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void registration(String login, String password, String address) {

            User user = new User(login, password, address);
            user.setDiscount(0);
            user.setWalletScore(2000.0);
            userDAO.registrationUser(user);
    }

    @Override
    public User getUserById(Long id) {

        return userDAO.getUserById(id);
    }

    @Override
    @Transactional
    public void pay(Long idUser, Double priceOrder) throws ServiceException{

        Double balance;
        if((balance = userDAO.getUserById(idUser).getWalletScore() - priceOrder) > 0.0) {
            userDAO.setBalance(idUser, balance);
        }
        else{
            throw new ServiceException(ServiceException.ERROR_USER_BALANCE);
        }
    }
}
