package be.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cust")
    @SequenceGenerator(name = "cust", sequenceName = "seq_order", allocationSize = 1, initialValue = 1)
    private Long id_order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order",
            orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL
    )
    private List<OrderPosition> orderPositions = new ArrayList<>();

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name ="status")
    private String status;

    public Order() {
        totalPrice = 0.0;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId_order() {
        return id_order;
    }

    public void setId_order(Long id_order) {
        this.id_order = id_order;
    }

    public List<OrderPosition> getOrderPositions() {
        return orderPositions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOrderPositions(List<OrderPosition> orderPositions) {

        for(OrderPosition orderPosition: orderPositions) {
            addOrderPosition(orderPosition);
        }
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addOrderPosition(OrderPosition orderPosition){
        this.orderPositions.add(orderPosition);
        orderPosition.setOrder(this);
    }
}
