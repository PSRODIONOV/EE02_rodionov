package be.business;

import be.entity.Flower;
import be.utils.FlowerFilter;
import be.utils.ServiceException;

import java.util.List;

public interface FlowerBusinessService {
    List<Flower> getAllFlowers();
    Flower getFlowerById(Long id) throws ServiceException;
    void setQuantity(Long idFlower, Long quantity) throws ServiceException;
    List<Flower> searchFilter(FlowerFilter filter);
    void increaseFlowersStockSize(Long count);
}
