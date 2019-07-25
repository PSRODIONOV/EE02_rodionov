package be.access;

import be.entity.Flower;
import be.utils.FlowerFilter;

import java.util.List;

public interface FlowerDAO {
    Flower getFlowerById(Long id);
    Flower getFlowerByName(String name);
    List<Flower> getAllFlowers();
    void update(Flower flower);
    void setQuantity(Long idFlower, Long quantity);
    List<Flower> searchFilter(FlowerFilter filter);
    void increaseFlowerStockSize(Long count);
}
