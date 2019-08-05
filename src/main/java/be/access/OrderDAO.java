package be.access;

import be.entity.Order;
import java.util.List;
import java.util.Optional;

public interface OrderDAO {

    void addOrder(Order order);
    Optional<Order> getOrderById(Long id);
    List<Order> getAllMyOrders(Long idUser);
    List<Order> getAllOrders();
}
