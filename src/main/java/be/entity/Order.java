package be.entity;

import javax.persistence.*;
import java.util.HashMap;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cust")
    @SequenceGenerator(name = "cust", sequenceName = "order_seq")
    private String id_order;
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;
    @Embedded
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ORDER_X_FLOWER", joinColumns = {@JoinColumn(name = "id_order")},inverseJoinColumns = {@JoinColumn(name = "id_flower")})
    private HashMap<Flower, Integer>  listItems;
    @Column(name = "TOTAL_PRICE")
    private double total_price;
}
