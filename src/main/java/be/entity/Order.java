package be.entity;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cust")
    @SequenceGenerator(name = "cust", sequenceName = "seq_order")
    private Long id_order;
    @Embedded
    @ManyToMany
    @JoinTable(name = "ORDERPOSITION")
    @JoinColumns({@JoinColumn(name = "id_order"), @JoinColumn(name = "id_flower")})
    private Set<OrderPosition> orderPositions;

    public Order(){};

    public Long getId_order() {
        return id_order;
    }

    public void setId_order(Long id_order) {
        this.id_order = id_order;
    }

    public Set<OrderPosition> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(Set<OrderPosition> orderPositions) {
        this.orderPositions = orderPositions;
    }
}
