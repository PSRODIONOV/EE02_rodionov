package be.access;

import be.entity.Flower;
import be.utils.FlowerFilter;

import java.util.List;
import java.util.Optional;

public interface FlowerDAO {
    Optional<Flower> getFlowerById(Long id);
    List<Flower> getAllFlowers();
    List<Flower> searchFilter(FlowerFilter filter);
    void increaseFlowerStockSize(Long count);
}
