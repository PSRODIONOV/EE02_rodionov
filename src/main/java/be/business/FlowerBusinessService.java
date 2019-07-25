package be.business;

import be.entity.Flower;
import be.utils.FlowerFilter;
import be.utils.ServiceException;

import java.util.List;

public interface FlowerBusinessService {
    List<Flower> getAllFlowers();
    Flower getFlowerById(Long id);
    Flower getFlowerByName(String name);
    void update(Flower flower);
    void setQuantity(Long idFlower, Long quantity);
    List<Flower> searchFilter(FlowerFilter filter);
    void increaseFlowersStockSize(Long count);
}
