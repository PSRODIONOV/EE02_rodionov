package be.business;

import be.access.UserDAO;
import be.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {

    @Autowired
    private UserDAO userDAO;

    private static final Logger LOG = LoggerFactory.getLogger(UserBusinessServiceImpl.class);

    public UserBusinessServiceImpl(){

        LOG.info("NOTIFICATION::" + this.getClass() + " IS CREATED.");
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
    public boolean registration(String login, String password, String address) {

        return userDAO.registrationUser(login, password, address);
    }

    @Override
    public void logout() {

    }
}
