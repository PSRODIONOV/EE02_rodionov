package be.access;

import be.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


@Repository
public class OrderDAOImpl implements OrderDAO {

    @PersistenceContext
    EntityManager em;

    private static final Logger LOG = LoggerFactory.getLogger(OrderDAOImpl.class);

    public OrderDAOImpl() {

        LOG.info(":::::::::"+this.getClass()+" IS CREATED::::::::: ");
    }

    @Override
    @Transactional
    public void addOrder(Order order) {
        em.persist(order);
        em.flush();
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        try {
            return Optional.of(em.find(Order.class, id));
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Order> getAllMyOrders(Long idUser) {

        TypedQuery<Order> q = em.createQuery("select o from Order o where o.user.idUser = :id", Order.class);
        q.setParameter("id", idUser);
        return q.getResultList();
    }

    @Override
    public List<Order> getAllOrders(){
        TypedQuery<Order> q = em.createQuery("select o from Order o order by (o.dateCreate, o.status)", Order.class);
        return q.getResultList();
    }
}
