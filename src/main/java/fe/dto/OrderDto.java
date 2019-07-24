package fe.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OrderDto {

    private Long idOrder;
    private UserDto userDto;
    private List<OrderPositionDto> orderPositions;
    private Double totalPrice;
    private String status;
    private String dateCreate;
    private String dateClose;

    public OrderDto() {
        this.orderPositions = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public List<OrderPositionDto> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(List<OrderPositionDto> orderPositions) {
        this.orderPositions = orderPositions;
    }


    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        if(dateCreate != null) {
            this.dateCreate = dateCreate.toString();
        }
        else {
            this.dateCreate = "--.--.----";
        }
    }

    public String getDateClose() {
        return dateClose;
    }

    public void setDateClose(Date dateClose) {
        if(dateClose != null) {
            this.dateClose = dateClose.toString();
        }
        else {
            this.dateClose = "--.--.----";
        }
    }

    public void addOrderPosition(OrderPositionDto newOrderPositionDto){
        for (OrderPositionDto orderPositionDto: orderPositions) {
            if(orderPositionDto.getFlowerDto().getIdFlower() == newOrderPositionDto.getFlowerDto().getIdFlower()){
                orderPositionDto.setQuantity(orderPositionDto.getQuantity() + newOrderPositionDto.getQuantity());
                return;
            }
        }
        this.orderPositions.add(newOrderPositionDto);
        this.totalPrice += newOrderPositionDto.getFlowerDto().getPrice()*newOrderPositionDto.getQuantity();
    }

    public void removeOrderPosition(Long id){

        for (OrderPositionDto orderPositionDto: this.orderPositions) {
            if(orderPositionDto.getFlowerDto().getIdFlower() == id){
                this.orderPositions.remove(orderPositionDto);
                this.totalPrice -= orderPositionDto.getFlowerDto().getPrice()*orderPositionDto.getQuantity();
                return;
            }
        }
    }
}
