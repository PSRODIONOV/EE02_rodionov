package be.business;

import be.access.UserDAO;
import be.entity.User;
import be.utils.ServiceException;
import be.utils.UserMarshallingServiceImpl;
import be.utils.enums.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.math.BigDecimal;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserMarshallingServiceImpl userMarshgallingService;

    private static final Logger LOG = LoggerFactory.getLogger(UserBusinessServiceImpl.class);

    public UserBusinessServiceImpl(){

        LOG.info(":::::::::" + this.getClass() + " IS CREATED:::::::::");
    }

    @Override
    public User login(String login, String password) {
        User user;
        try {
            if ((user = userDAO.getUserByLogin(login)) != null) {
                if (user.getPassword().equals(password)) {
                    return user;
                }
            }
        }
        catch (NoResultException e) {
            return null;
        }
        return null;
    }

    @Override
    public void registration(String login, String password, String address) throws ServiceException{

            User user = new User(login, password, address);
            user.setDiscount(5);
            user.setWalletScore(new BigDecimal(2000));
            user.setRole(UserType.USER);
            try{
                if(userDAO.getUserByLogin(user.getLogin()) != null) {
                    throw new ServiceException(ServiceException.ERROR_USER_REGISTRATION);
                }
            }
            catch(NoResultException e){
                userDAO.registrationUser(user);
                userMarshgallingService.doMarshaling(login, user);
            }


    }

    @Override
    public User getUserById(Long id) {

        return userDAO.getUserById(id);
    }

    @Override
    @Transactional
    public void pay(Long idUser, BigDecimal priceOrder) throws ServiceException{

        BigDecimal balance;
        if((balance = userDAO.getUserById(idUser).getWalletScore().subtract(priceOrder)).compareTo(new BigDecimal(0)) != -1) {
            userDAO.setBalance(idUser, balance);
        }
        else{
            throw new ServiceException(ServiceException.ERROR_USER_BALANCE);
        }
    }
}
