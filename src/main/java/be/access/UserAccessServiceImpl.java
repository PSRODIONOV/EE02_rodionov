package be.access;

import be.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Component
public class UserAccessServiceImpl implements UserAccessService {

    //Map<String, User> userStorage;

    @PersistenceContext
    EntityManager em;

    private static final Logger LOG = LoggerFactory.getLogger(UserAccessServiceImpl.class);

    public UserAccessServiceImpl() {

        /*userStorage = new HashMap<>();
        userStorage.put("user", new User("user", "1234", "user@mail.com"));*/
        LOG.info("NOTIFICATION::"+this.getClass()+" IS CREATED.");
    }

    @Override
    public User getUserByLogin(String login) {

        User findUser;
        if((findUser = userStorage.get(login)) != null){
            return findUser;
        }
        return null;
    }

    @Override
    public boolean registrationUser(String login, String password, String address) {

        em.persist(new User(login, password, address));
        em.flush();
        return true;
        /*if(userStorage.get(login) == null){
            User newUser = new User(login, password, address);
            newUser.setWallet_score(2000);
            newUser.setDiscount(0);
            userStorage.put(login, newUser);
            return true;
        }
        return false;
         */

    }
}
