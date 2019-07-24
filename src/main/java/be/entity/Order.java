package be.entity;

import javax.persistence.*;
import java.sql.Date;
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
    private Double totalPrice;

    @Column(name ="status")
    private String status;

    @Column(name = "date_create")
    private Date dateCreate;

    @Column(name = "date_close")
    private Date dateClose;

    public Order() {
        totalPrice = 0.0;
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

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateClose() {
        return dateClose;
    }

    public void setDateClose(Date dateClose) {
        this.dateClose = dateClose;
    }
}
