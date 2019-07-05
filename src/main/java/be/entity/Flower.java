package be.entity;

import javax.persistence.*;

@Entity
@Table(name = "FLOWERS")
public class Flower {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cust")
    @SequenceGenerator(name = "cust", sequenceName = "flower_seq")
    private String id_flower;
    @Column(name = "NAME_FLOWER")
    private String name_flower;
    @Column(name = "PRICE")
    private double price;
    @Column(name = "QUANTITY")
    private int quantity;

    public Flower(){};

    public String getId_flower() {
        return id_flower;
    }

    public void setId_flower(String id_flower) {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
