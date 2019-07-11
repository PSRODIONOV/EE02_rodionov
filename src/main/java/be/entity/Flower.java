package be.entity;

import javax.persistence.*;

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
    private Double price;
    @Column(name = "QUANTITY")
    private Long quantity;

    public Flower() {
    }

    ;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
