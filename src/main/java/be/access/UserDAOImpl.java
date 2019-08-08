package be.access;

import be.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    private static final Logger LOG = LoggerFactory.getLogger(UserDAOImpl.class);

    public UserDAOImpl() {

        LOG.info(":::::::::" + this.getClass() + " IS CREATED:::::::::");
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        try {
            TypedQuery<User> q = em.createQuery("Select u from User u where u.login = :login", User.class);
            q.setParameter("login", login);
            return Optional.of(q.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getUserById(Long id) {
        try {
            return Optional.of(em.find(User.class, id));
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public void registrationUser(User newUser) {
        em.persist(newUser);
        em.flush();
    }

}
