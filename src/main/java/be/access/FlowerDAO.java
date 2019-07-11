package be.access;

import be.entity.Flower;

import java.util.List;

public interface FlowerDAO {
    Flower getFlowerById(Long id);
    Flower getFlowerByName(String name);
    List<Flower> getAllFlowers();
}
