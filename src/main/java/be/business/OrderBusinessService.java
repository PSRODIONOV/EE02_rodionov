package be.business;

import be.entity.Order;

public interface OrderBusinessService {

   void addOrder(Order order);
   void updateOrder(Order order);
   Order getOrderById(Long id);
   void delOrderById(Long id);
}
