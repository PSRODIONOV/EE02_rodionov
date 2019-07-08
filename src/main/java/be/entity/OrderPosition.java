package be.entity;

import javax.persistence.*;

@Embeddable
@Entity
@Table(name = "ORDERPOSITION")
public class OrderPosition {

    @EmbeddedId
    private KeyPos key;
    @Column(name = "quantity")
    private Long quantity;

    public OrderPosition(){};

    public KeyPos getKey() {
        return key;
    }

    public void setKey(KeyPos key) {
        this.key = key;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
