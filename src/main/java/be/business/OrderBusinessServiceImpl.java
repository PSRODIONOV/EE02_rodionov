package be.business;

import be.access.OrderAccessService;
import be.entity.Flower;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderBusinessServiceImpl implements OrderBusinessService {

    @Autowired
    public OrderAccessService oas;

    private static final Logger LOG = LoggerFactory.getLogger(OrderBusinessServiceImpl.class);

    public OrderBusinessServiceImpl() {

        LOG.info("NOTIFICATION::"+this.getClass()+" IS CREATED.");
    }

    @Override
    public void addOrder(Flower flower) {

    }

    @Override
    public void updateOrder(Flower flower) {

    }

    @Override
    public void searchOrder(String key, String value) {

    }

    @Override
    public void removeOrder(long id) {

    }

    @Override
    public List<Flower> getAllMyOrders() {
        return null;
    }
}
