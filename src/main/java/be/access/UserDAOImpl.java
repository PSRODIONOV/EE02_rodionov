package be.access;

import be.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@Repository
public class UserDAOImpl implements UserDAO {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Users");
    @PersistenceContext
    EntityManager em = entityManagerFactory.createEntityManager();

    private static final Logger LOG = LoggerFactory.getLogger(UserDAOImpl.class);

    public UserDAOImpl() {

        LOG.info("NOTIFICATION::"+this.getClass()+" IS CREATED.");
    }

    @Override
    public User getUserByLogin(String login) {

        User findUser = em.find(User.class, login);
        return findUser;
    }

    @Override
    @Transactional
    public boolean registrationUser(String login, String password, String address) {

        User newUser = new User(login, password, address);
        newUser.setDiscount(0);
        newUser.setWallet_score(2000.0);
        em.persist(newUser);
        em.flush();
        return true;
    }
}
