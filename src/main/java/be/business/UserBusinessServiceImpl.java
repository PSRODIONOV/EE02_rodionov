package be.business;

import be.access.UserDAO;
import be.access.repositories.UserRepository;
import be.entity.User;
import be.utils.ServiceException;
import be.utils.enums.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger LOG = LoggerFactory.getLogger(UserBusinessServiceImpl.class);

    public UserBusinessServiceImpl() {

        LOG.info(":::::::::" + this.getClass() + " IS CREATED:::::::::");
    }

    @Override
    public Boolean checkLogin(String login) {
        return userRepository.getUserByLogin(login).isPresent();
    }

    @Override
    public User login(String login, String password) throws ServiceException {
        User user = userRepository.getUserByLogin(login)
                .orElseThrow(() -> new ServiceException(ServiceException.ERROR_FIND_USER));
        if (password.equals(user.getPassword())) {
            return user;
        }
        throw new ServiceException(ServiceException.ERROR_USER_LOGIN);
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void registration(String login, String password, String address) throws ServiceException {

        User user = new User(login, password, address);
        user.setDiscount(5);
        user.setWalletScore(new BigDecimal(2000));
        user.setRole(UserType.USER);

        if (userRepository.getUserByLogin(user.getLogin()).isPresent()) {
            throw new ServiceException(ServiceException.ERROR_USER_REGISTRATION);
        }
        try {
            userRepository.saveAndFlush(user);
        }
        catch (Exception e) {
            throw new ServiceException(ServiceException.ERROR_USER_REGISTRATION);
        }
    }

    @Override
    public User getUserById(Long id) throws ServiceException {

        return userRepository.findById(id).orElseThrow(() -> new ServiceException(ServiceException.ERROR_FIND_USER));
    }

    @Override
    public User getUserByLogin(String login) throws ServiceException {
        return userRepository.getUserByLogin(login).orElseThrow(() -> new ServiceException(ServiceException.ERROR_FIND_USER));
    }

    @Override
    public void pay(Long idUser, BigDecimal priceOrder) throws ServiceException {

        BigDecimal balance;
        User user = getUserById(idUser);
        if ((balance = user.getWalletScore().subtract(priceOrder)).compareTo(BigDecimal.ZERO) != -1) {
            user.setWalletScore(balance);
        } else {
            throw new ServiceException(ServiceException.ERROR_USER_BALANCE);
        }
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void updateDiscount(Long idUser, Integer newDiscount) throws ServiceException {
        if(newDiscount < 0){
            throw new ServiceException(ServiceException.ERROR_INVALIDATE_DATA);
        }
        User user = getUserById(idUser);
        user.setDiscount(newDiscount);
    }
}
