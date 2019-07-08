package be.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "FLOWERS")
public class Flower {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cust")
    @SequenceGenerator(name = "cust", sequenceName = "seq_flower")
    private Long id_flower;
    @Column(name = "NAME_FLOWER")
    private String name_flower;
    @Column(name = "PRICE")
    private double price;
    @Column(name = "QUANTITY")
    private Long quantity;
    @Embedded
    @ManyToMany(mappedBy = "ORDER")
    private Set<Order> orders;

    public Flower(){};

    public Long getId_flower() {
        return id_flower;
    }

    public void setId_flower(Long id_flower) {
        this.id_flower = id_flower;
    }

    public String getName_flower() {
        return name_flower;
    }

    public void setName_flower(String name_flower) {
        this.name_flower = name_flower;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
