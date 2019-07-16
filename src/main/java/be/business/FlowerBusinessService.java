package be.business;

import be.entity.Flower;

import java.util.List;

public interface FlowerBusinessService {
    List<Flower> getAllFlowers();
    public Flower getFlowerById(Long id);
    public Flower getFlowerByName(String name);
}
