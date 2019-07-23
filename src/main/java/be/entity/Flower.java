package be.entity;

import javax.persistence.*;

@Entity
@Table(name = "FLOWERS")
public class Flower {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cust")
    @SequenceGenerator(name = "cust", sequenceName = "seq_flower",allocationSize = 1, initialValue = 1)
    @Column(name = "ID_FLOWER")
    private Long idFlower;
    @Column(name = "NAME_FLOWER")
    private String nameFlower;
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "QUANTITY")
    private Long quantity;

    public Flower() {
    }

    public Long getIdFlower() {
        return idFlower;
    }

    public void setIdFlower(Long idFlower) {
        this.idFlower = idFlower;
    }

    public String getNameFlower() {
        return nameFlower;
    }

    public void setNameFlower(String nameFlower) {
        this.nameFlower = nameFlower;
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
