package fe.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {

    private Long id_order;
    private UserDto userDto;
    private List<OrderPositionDto> orderPositions;
    private Double totalPrice;

    public OrderDto() {
        orderPositions = new ArrayList<>();
        totalPrice = 0.0;
    }

    public Long getId_order() {
        return id_order;
    }

    public void setId_order(Long id_order) {
        this.id_order = id_order;
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

    public void addOrderPosition(OrderPositionDto newOrderPositionDto){
        for (OrderPositionDto orderPositionDto: orderPositions) {
            if(orderPositionDto.getFlowerDto().getId_flower() == newOrderPositionDto.getFlowerDto().getId_flower()){
                orderPositionDto.setQuantity(orderPositionDto.getQuantity() + newOrderPositionDto.getQuantity());
                return;
            }
        }
        this.orderPositions.add(newOrderPositionDto);
    }

    public void removeOrderPosition(Long id){

        for (OrderPositionDto orderPositionDto: this.orderPositions) {
            if(orderPositionDto.getFlowerDto().getId_flower() == id){
                this.orderPositions.remove(orderPositionDto);
                return;
            }
        }
    }
}
