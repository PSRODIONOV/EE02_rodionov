package fe.dto;

import be.utils.Mapper;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderPositionDto {

    private Long idOrder;
    private FlowerDto flowerDto;
    private Long quantity;
    private BigDecimal price;

    public OrderPositionDto(){
    }

    public OrderPositionDto(Long idOrder, FlowerDto flower, Long quantity){
        this.quantity = quantity;
        this.idOrder = idOrder;
        this.flowerDto = flower;
        computePrice();
    }

    public FlowerDto getFlowerDto() {
        return flowerDto;
    }

    public void setFlowerDto(FlowerDto flowerDto) {
        this.flowerDto = flowerDto;
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
        BigDecimal price = flowerDto.getPrice().multiply(new BigDecimal(quantity));
        this.setPrice(price.setScale(2, RoundingMode.HALF_DOWN));
    }
}
