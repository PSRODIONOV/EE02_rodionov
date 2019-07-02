package be.business;

import be.entity.Flower;

import java.util.List;

public interface OrderBusinessService {

    public void addOrder(Flower flower);
    public void updateOrder(Flower flower);
    public void searchOrder(String key, String value);
    public void removeOrder(long id);
    public List<Flower> getAllMyOrders();
}
