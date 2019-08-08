package fe.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderPositionDto {

    private Long idOrder;
    private FlowerDto flower;
    private Long quantity;
    private BigDecimal price;

    public OrderPositionDto(){
    }

    public OrderPositionDto(Long idOrder, FlowerDto flower, Long quantity){
        this.quantity = quantity;
        this.idOrder = idOrder;
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

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
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
