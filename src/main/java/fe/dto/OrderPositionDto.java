package fe.dto;

public class OrderPositionDto {

    private Long idOrder;
    private FlowerDto flowerDto;
    private Long quantity;
    private Double price;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
