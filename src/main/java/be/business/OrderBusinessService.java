package be.business;

import be.entity.Order;
import be.entity.User;
import be.utils.ServiceException;

import java.util.List;

public interface OrderBusinessService {

   void addOrder(Order order) throws ServiceException;
   List<Order> getAllOrders(User user);
   void payOrder(Long idOrder, Long idUser) throws ServiceException;
   void closeOrder(Long idOrder) throws ServiceException;
}
