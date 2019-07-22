package be.business;

import be.access.FlowerDAO;
import be.entity.Flower;
import be.utils.FlowerFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowerBusinessServiceImpl implements FlowerBusinessService {

    @Autowired
    private FlowerDAO flowerDAO;

    private static final Logger LOG = LoggerFactory.getLogger(FlowerBusinessServiceImpl.class);

    public FlowerBusinessServiceImpl() {

        LOG.info(":::::::::"+this.getClass()+" IS CREATED:::::::::");
    }
    @Override
    public List<Flower> getAllFlowers() {
        return flowerDAO.getAllFlowers();
    }

    @Override
    public Flower getFlowerById(Long id){
        return flowerDAO.getFlowerById(id);
    }

    @Override
    public Flower getFlowerByName(String name){
        return flowerDAO.getFlowerByName(name);
    }

    @Override
    public void update(Flower flower){
        flowerDAO.update(flower);
    }

    @Override
    public void setQuantity(Long idFlower, Long quantity){
        flowerDAO.setQuantity(idFlower, quantity);
    }

    @Override
    public List<Flower> searchFilter(FlowerFilter filter) {
        return flowerDAO.searchFilter(filter);
    }
}
