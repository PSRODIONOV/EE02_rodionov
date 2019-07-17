package be.access;

import be.entity.Order;
import be.entity.OrderPosition;
import be.entity.User;
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
       /* Query q = em.createNativeQuery("insert into Orders values (seq_order.nextval, ?, ?)");
        //q.setParameter(1, order.getId_order());
        q.setParameter(1, order.getUser().getId_user());
        q.setParameter(2, order.getTotalPrice());
        q.executeUpdate();
        Long id;
        q = em.createNativeQuery("select * from Orders order by id_order desc limit(1)");
        id = (Long)q.getSingleResult();
        for(OrderPosition op: order.getOrderPositions()) {
            OrderPosition orderPosition = new OrderPosition();
            orderPosition.setOrder(getOrderById(id));
            orderPosition.setQuantity(op.getQuantity());
            orderPosition.setFlower(op.getFlower());
            q = em.createQuery("insert into ORDERPOSITION values (:orderPosition)");
            q.setParameter("orderposition", orderPosition);
        }

        */
        em.flush();
    }

    @Override
    public void updateOrder(Order order) {
        em.merge(order);
        em.flush();
    }

    @Override
    public Order getOrderById(Long id)
    {
        return em.find(Order.class, id);
    }

    @Override
    public void delOrder(Order order) {

        em.remove(order);
        em.flush();
    }

    @Override
    public void delOrderById(Long id) {

        em.remove(em.find(Order.class, id));
        em.flush();
    }

    @Override
    public List<Order> getAllOrders() {

        TypedQuery<Order> q = em.createQuery("select o from Order o", Order.class);
        return q.getResultList();
    }

    @Override
    public List<Order> getAllMyOrders(User user) {

        TypedQuery<Order> q = em.createQuery("select o from Order o where o.user.id_user = :id", Order.class);
        q.setParameter("id", user.getId_user());
        return q.getResultList();
    }
}
