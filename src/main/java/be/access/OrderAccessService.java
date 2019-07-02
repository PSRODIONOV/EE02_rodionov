package be.access;

import be.entity.Flower;
import be.entity.User;

import java.util.List;

public interface OrderAccessService {

    void addFlower(Flower flower);
    void updateFlower(Flower flower);
    List<Flower> searchFlower(String key, String value);
    void removeFlower(long id, User user);
    List<Flower> getAllFlowers();
}
