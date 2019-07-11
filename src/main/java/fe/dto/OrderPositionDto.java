package fe.dto;

import be.entity.OrderPosition;

public class OrderPositionDto {

    private Long id_flower;
    private Long quantity;
    private Double price; //For unit

    public OrderPositionDto(OrderPosition order){
        this.id_flower = order.getFlower().getId_flower();
        this.quantity = order.getQuantity();
        this.price = order.getFlower().getPrice();
    }

    public Long getId_flower() {
        return id_flower;
    }

    public void setId_flower(Long id_flower) {
        this.id_flower = id_flower;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
