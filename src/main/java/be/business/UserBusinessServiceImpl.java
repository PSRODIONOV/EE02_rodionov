package be.business;

import be.access.UserAccessService;
import be.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class UserBusinessServiceImpl implements UserBusinessService {

    @Autowired
    public UserAccessService uas;

    private User currentUser;

    private static final Logger LOG = LoggerFactory.getLogger(UserBusinessServiceImpl.class);


    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public UserBusinessServiceImpl(){

        currentUser = null;
        LOG.info("NOTIFICATION::" + this.getClass() + " IS CREATED.");

    }

    @Override
    public boolean login(String login, String password) {
        if((currentUser = uas.getUserByLogin(login))!= null) {
            if(currentUser.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean registration(String login, String password, String address) {

        return uas.registrationUser(login, password, address);
    }

    @Override
    public void logout() {
        currentUser = null;
    }
}
