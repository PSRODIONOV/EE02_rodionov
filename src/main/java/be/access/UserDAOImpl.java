package be.access;

import be.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.math.BigDecimal;
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
    @Transactional
    public Optional<User> getUserByLogin(String login) {
        try {
            TypedQuery<User> q = em.createQuery("Select u from User u where u.login = :login", User.class);
            q.setParameter("login", login);
            return Optional.of(q.getSingleResult());
        }
        catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Optional<User> getUserById(Long id) {
        try {
            return Optional.of(em.find(User.class, id));
        }
        catch(NoResultException e){
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void registrationUser(User newUser) {
            em.persist(newUser);
            em.flush();
    }

    @Override
    @Transactional
    public void setBalance(Long id, BigDecimal balance){
        Query q = em.createQuery("update User u set u.walletScore = :balance where u.id = :id");
        q.setParameter("id", id);
        q.setParameter("balance", balance);
        q.executeUpdate();
        em.flush();
    }

    @Override
    @Transactional
    public void setDiscount(Long id, Integer discount){
        Query q = em.createQuery("update User u set u.discount = :discount where u.id = :id");
        q.setParameter("id", id);
        q.setParameter("discount", discount);
        q.executeUpdate();
        em.flush();
    }

}
