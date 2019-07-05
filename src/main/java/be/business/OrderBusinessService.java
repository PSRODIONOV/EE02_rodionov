package be.business;

import be.entity.Flower;

import java.util.List;

public interface OrderBusinessService {

   void addOrder(Flower flower);
   void updateOrder(Flower flower);
   void searchOrder(String key, String value);
   void removeOrder(long id);
   List<Flower> getAllMyOrders();
}
