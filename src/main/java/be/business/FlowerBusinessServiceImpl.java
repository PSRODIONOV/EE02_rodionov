package be.business;

import be.access.repositories.FlowerRepository;
import be.entity.Flower;
import be.utils.FlowerFilter;
import be.utils.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FlowerBusinessServiceImpl implements FlowerBusinessService {

    @Autowired
    private FlowerRepository flowerRepository;

    private static final Logger LOG = LoggerFactory.getLogger(FlowerBusinessServiceImpl.class);

    public FlowerBusinessServiceImpl() {

        LOG.info(":::::::::" + this.getClass() + " IS CREATED:::::::::");
    }

    @Override
    public List<Flower> getAllFlowers() {
        return flowerRepository.findAll();
    }

    @Override
    public Flower getFlowerById(Long id) throws ServiceException {

        return flowerRepository.findById(id).orElseThrow(() -> new ServiceException(ServiceException.ERROR_FIND_FLOWER));
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void setQuantity(Long idFlower, Long quantity) throws ServiceException {
        Flower flower = getFlowerById(idFlower);
        flower.setQuantity(quantity);
    }

    @Override
    public List<Flower> searchFilter(FlowerFilter filter) {
        return flowerRepository.getFlowerByFilter(new BigDecimal(filter.getMinPrice()), new BigDecimal(filter.getMaxPrice()), filter.getName());
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void increaseFlowersStockSize(Long count) {

        List<Flower> flowerList = flowerRepository.findAll();
        for (Flower flower : flowerList) {
            flower.setQuantity(flower.getQuantity() + count);
        }
    }
}
