package be.access;

import be.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    private static final Logger LOG = LoggerFactory.getLogger(UserDAOImpl.class);

    public UserDAOImpl() {

        LOG.info(":::::::::" + this.getClass() + " IS CREATED:::::::::");
    }

    @Override
    @Transactional
    public User getUserByLogin(String login) {

        TypedQuery<User> q = em.createQuery("Select u from User u where u.login = :login", User.class);
        q.setParameter("login", login);
        return q.getSingleResult();
    }

    @Override
    @Transactional
    public void registrationUser(User newUser) {
        em.persist(newUser);
        em.flush();
    }
}
