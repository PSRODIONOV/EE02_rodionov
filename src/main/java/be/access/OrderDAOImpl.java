package be.access;

import be.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


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
    @Transactional
    public void updateOrder(Order order) {
        em.merge(order);
        em.flush();
    }

    @Override
    @Transactional
    public void updateStatus(Long idOrder, String status){
        Query q = em.createQuery("update Order o set o.status = :status where o.idOrder = :idOrder");
        q.setParameter("idOrder", idOrder);
        q.setParameter("status", status);
        q.executeUpdate();
        em.flush();
    }

    @Override
    public Order getOrderById(Long id)
    {
        return em.find(Order.class, id);
    }

    @Override
    @Transactional
    public void delOrder(Order order) {

        em.remove(order);
        em.flush();
    }

    @Override
    @Transactional
    public void delOrderById(Long id) {

        em.remove(em.find(Order.class, id));
        em.flush();
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
