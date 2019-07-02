package be.access;

import be.entity.Flower;
import be.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class OrderAccessServiceImpl implements OrderAccessService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderAccessServiceImpl.class);

    public OrderAccessServiceImpl() {

        LOG.info("NOTIFICATION::"+this.getClass()+" IS CREATED");
    }

    @Override
    public void addFlower(Flower flower) {

    }

    @Override
    public void updateFlower(Flower flower) {

    }

    @Override
    public List<Flower> searchFlower(String key, String value) {
        return null;
    }

    @Override
    public void removeFlower(long id, User user) {

    }

    @Override
    public List<Flower> getAllFlowers() {
        return null;
    }
}
