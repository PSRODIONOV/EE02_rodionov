package be.entity;

import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.io.Serializable;


@Embeddable
public class KeyPos implements Serializable {


    @Column(name = "id_order", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cust")
    @SequenceGenerator(name = "cust", sequenceName = "seq_order")
    private Long idOrder;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_flower", nullable = false)
    private Flower flower;

    public KeyPos(){

    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }

}
