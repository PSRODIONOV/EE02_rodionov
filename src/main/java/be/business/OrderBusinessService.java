package be.business;

import be.entity.Order;
import be.entity.OrderPosition;
import be.entity.User;

import java.util.List;

public interface OrderBusinessService {

   void addOrder(Order order);
   void updateOrder(Order order);
   Order getOrderById(Long id);
   void delOrderById(Long id);
   List<Order> getAllOrders(User user);
   void payOrder(Long idOrder, Long idUser);
   void closeOrder(Long idOrder);
}
