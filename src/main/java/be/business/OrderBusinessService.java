package be.business;

import be.entity.Order;
import be.entity.User;
import be.utils.ServiceException;

import java.util.List;

public interface OrderBusinessService {

   void addOrder(Order order) throws ServiceException;
   void updateOrder(Order order);
   Order getOrderById(Long id);
   void delOrderById(Long id);
   List<Order> getAllOrders(User user);
   void payOrder(Long idOrder, Long idUser) throws ServiceException;
   void closeOrder(Long idOrder);
}
