package be.entity;

import javax.persistence.*;
import java.io.Serializable;


@Embeddable
public class KeyPos implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_order", nullable = false)
    private Order order;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_flower", nullable = false)
    private Flower flower;

    public KeyPos(){};

    public KeyPos(Order order, Flower flower){
        this.order = order;
        this.flower = flower;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }

}
