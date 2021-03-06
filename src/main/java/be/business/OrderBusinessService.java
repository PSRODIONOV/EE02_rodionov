package be.business;

import be.entity.Order;
import be.entity.User;
import be.utils.ServiceException;

import java.util.List;

public interface OrderBusinessService {

   void addOrder(Order order) throws ServiceException;
   List<Order> getAllOrders();
   List<Order> getAllOrders(User user);
   Order getOrderById(Long id) throws ServiceException;
   List<Order> getUserOrders(Long idUser) throws ServiceException;
   void payOrder(Long idOrder) throws ServiceException;
   void closeOrder(Long idOrder) throws ServiceException;
}
