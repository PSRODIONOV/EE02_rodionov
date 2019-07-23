package be.access;

import be.entity.Order;
import java.util.List;

public interface OrderDAO {

    void addOrder(Order order);
    void updateOrder(Order order);
    public void updateStatus(Long idOrder, String status);
    Order getOrderById(Long id);
    void delOrder(Order order);
    void delOrderById(Long id);
    List<Order> getAllMyOrders(Long idUser);
    List<Order> getAllOrders();
}
