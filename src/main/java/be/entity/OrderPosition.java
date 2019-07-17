package be.entity;

import javax.persistence.*;

@Entity
@Table(name = "ORDERPOSITION")
@IdClass(KeyPos.class)
public class OrderPosition {

    @Id
    @AttributeOverrides({
            @AttributeOverride(name = "idOrder",
                    column = @Column(name="id_order")),
            @AttributeOverride(name = "flower",
                    column = @Column(name="id_flower"))
    })
    private Long idOrder;
    private Flower flower;

    @Column(name = "quantity")
    private Long quantity;


    public OrderPosition(){

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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
