package be.business;

import be.access.OrderDAO;
import be.entity.Order;
import be.entity.OrderPosition;
import be.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class OrderBusinessServiceImpl implements OrderBusinessService {

    @Autowired
    public OrderDAO orderDAO;

    private static final Logger LOG = LoggerFactory.getLogger(OrderBusinessServiceImpl.class);

    public OrderBusinessServiceImpl() {

        LOG.info(":::::::::"+this.getClass()+" IS CREATED:::::::::");
    }

    @Override
    public void addOrder(Order order) {
        orderDAO.addOrder(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }

    @Override
    public Order getOrderById(Long id) {

        return orderDAO.getOrderById(id);
    }

    @Override
    public void delOrderById(Long id) {
        orderDAO.delOrderById(id);
    }

    @Override
    public List<Order> getAllMyOrders(User user) {
        return orderDAO.getAllMyOrders(user);
    }

    @Override
    public void payOrder(Order order){
        if(order.getStatus().equals("not paid")){
            order.setStatus("paid");
            orderDAO.updateOrder(order);
        }

    }
}
