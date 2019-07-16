package be.business;

import be.access.FlowerDAO;
import be.entity.Flower;
import com.sun.tools.javac.comp.Flow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowerBusinessServiceImpl implements FlowerBusinessService {

    @Autowired
    FlowerDAO flowerDAO;

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

}
