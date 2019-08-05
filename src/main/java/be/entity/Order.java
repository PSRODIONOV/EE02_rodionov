package be.entity;

import be.utils.enums.OrderStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cust")
    @SequenceGenerator(name = "cust", sequenceName = "seq_order", allocationSize = 1, initialValue = 1)
    @Column(name = "id_order")
    private Long idOrder;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;

    @OneToMany(mappedBy = "order",
            orphanRemoval = true, fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    private List<OrderPosition> orderPositions = new ArrayList<>();

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name ="status")
    private OrderStatus status;


    @Column(name = "date_create")
    private LocalDateTime dateCreate;

    @Column(name = "date_close")
    private LocalDateTime dateClose;

    public Order() {
        totalPrice = new BigDecimal(0);
        this.dateCreate = LocalDateTime.now();
        this.status = OrderStatus.CREATED;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public List<OrderPosition> getOrderPositions() {
        return orderPositions;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setOrderPositions(List<OrderPosition> orderPositions) {

        for(OrderPosition orderPosition: orderPositions) {
            addOrderPosition(orderPosition);
        }
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addOrderPosition(OrderPosition orderPosition){
        this.orderPositions.add(orderPosition);
        orderPosition.setOrder(this);
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public LocalDateTime getDateClose() {
        return dateClose;
    }

    public void close(){
        this.status = OrderStatus.CLOSED;
        this.dateClose = LocalDateTime.now();
    }
}
