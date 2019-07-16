package be.access;

import be.entity.Order;
import be.entity.User;

import java.util.List;

public interface OrderDAO {

    void addOrder(Order order);
    void updateOrder(Order order);
    Order getOrderById(Long id);
    void delOrder(Order order);
    void delOrderById(Long id);
    List<Order> getAllOrders();
    List<Order> getAllMyOrders(User user);

}
