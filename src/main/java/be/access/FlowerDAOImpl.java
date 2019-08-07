package be.access;

import be.entity.Flower;
import be.utils.FlowerFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class FlowerDAOImpl implements FlowerDAO {

    @PersistenceContext
    EntityManager em;

    private static final Logger LOG = LoggerFactory.getLogger(FlowerDAOImpl.class);

    public FlowerDAOImpl() {

        LOG.info(":::::::::"+this.getClass()+" IS CREATED::::::::: ");
    }

    @Override
    public Optional<Flower> getFlowerById(Long id) {
        try {
            return Optional.of(em.find(Flower.class, id));
        }
        catch (NoResultException e){
            return Optional.empty();
        }
    }


    @Override
    public List<Flower> getAllFlowers() {
        TypedQuery<Flower> q = em.createQuery("select f from Flower f", Flower.class);
        return q.getResultList();
    }

    @Override
    public List<Flower> searchFilter(FlowerFilter filter) {
        String temp = filter.toString();
        TypedQuery<Flower> q = em.createQuery("select f from Flower f where " + temp, Flower.class);
        q.setParameter("minprice", new BigDecimal(filter.getMinPrice()));
        q.setParameter("maxprice", new BigDecimal(filter.getMaxPrice()));
        q.setParameter("name", filter.getName());
        return q.getResultList();
    }

}
