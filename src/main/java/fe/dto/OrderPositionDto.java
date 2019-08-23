package fe.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderPositionDto {

    @JsonIgnore
    private OrderDto order;
    private FlowerDto flower;
    private Long quantity;
    private BigDecimal price;

    public OrderPositionDto(){
    }

    public OrderPositionDto(OrderDto order, FlowerDto flower, Long quantity){
        this.quantity = quantity;
        this.order = order;
        this.flower = flower;
        computePrice();
    }

    public FlowerDto getFlower() {
        return flower;
    }

    public void setFlower(FlowerDto flower) {
        this.flower = flower;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public OrderDto getOrder() {
        return order;
    }

    public void setOrder(OrderDto order) {
        this.order= order;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void computePrice(){
        BigDecimal price = flower.getPrice().multiply(new BigDecimal(quantity));
        this.setPrice(price.setScale(2, RoundingMode.HALF_DOWN));
    }
}
