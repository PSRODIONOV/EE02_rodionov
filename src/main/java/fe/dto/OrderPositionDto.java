package fe.dto;

import java.math.BigDecimal;

public class OrderPositionDto {

    private Long idOrder;
    private FlowerDto flowerDto;
    private Long quantity;
    private BigDecimal price;
    private BigDecimal priceWithDiscount;

    public OrderPositionDto(){
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

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
