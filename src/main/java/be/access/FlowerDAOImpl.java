package be.access;

import be.entity.Flower;
import be.utils.FlowerFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
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
    public void setQuantity(Long idFlower, Long quantity){

        Query q = em.createQuery("update Flower f set f.quantity = :quantity where f.idFlower = :idFlower");
        q.setParameter("quantity", quantity);
        q.setParameter("idFlower", idFlower);
        q.executeUpdate();
        em.flush();
    }

    @Override
    public List<Flower> searchFilter(FlowerFilter filter) {
        String temp = filter.toString();
        TypedQuery<Flower> q = em.createQuery("select f from Flower f " + temp, Flower.class);
        return q.getResultList();
    }

    @Override
    public void increaseFlowerStockSize(Long count) {
        //todo move business
        Query q = em.createQuery("update Flower f set f.quantity = f.quantity + :count");
        q.setParameter("count", count);
        q.executeUpdate();
        em.flush();
    }
}
