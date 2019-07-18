package be.business;

import be.entity.Order;
import be.entity.User;

import java.util.List;

public interface OrderBusinessService {

   void addOrder(Order order);
   void updateOrder(Order order);
   Order getOrderById(Long id);
   void delOrderById(Long id);
   List<Order> getAllMyOrders(User user);
   void payOrder(Order order);
}
