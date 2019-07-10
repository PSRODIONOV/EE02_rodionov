package be.entity;

import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cust")
    @SequenceGenerator(name = "cust", sequenceName = "seq_order")
    private Long id_order;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    //@OneToMany(fetch = FetchType.EAGER)
    //@JoinTable(name = "ORDERPOSITION", joinColumns = {@JoinColumn(name = "id_order")}, inverseJoinColumns = {@JoinColumn(name = "id_order"), @JoinColumn(name = "id_flower")})
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OrderPosition> orderPositions;

    public Order(){};

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

    public Set<OrderPosition> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(Set<OrderPosition> orderPositions) {
        this.orderPositions = orderPositions;
    }
}
