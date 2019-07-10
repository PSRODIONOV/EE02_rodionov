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

    //EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Users");
    @PersistenceContext
    EntityManager em;

    private static final Logger LOG = LoggerFactory.getLogger(UserDAOImpl.class);

    public UserDAOImpl() {

        LOG.info(":::::::::"+this.getClass()+" IS CREATED:::::::::");
    }

    @Override
    public User getUserByLogin(String login) {

        return em.find(User.class, login);
    }

    @Override
    @Transactional
    public void registrationUser(User newUser) {
        em.persist(newUser);
        em.flush();
    }
}
