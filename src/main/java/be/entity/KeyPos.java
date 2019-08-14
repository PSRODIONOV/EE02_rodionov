package be.entity;

import javax.persistence.*;
import java.io.Serializable;


@Embeddable
public class KeyPos implements Serializable {

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_order")
    private Order order;
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_flower")
    private Flower flower;

    public KeyPos() {}

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
