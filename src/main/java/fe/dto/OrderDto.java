package fe.dto;

import be.entity.OrderPosition;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {

    private Long id_order;
    private Long id_user;
    private List<OrderPositionDto> orderPositions;
    private Double total_price;

    public OrderDto() {
        orderPositions = new ArrayList<>();
        total_price = 0.0;
    }

    public Long getId_order() {
        return id_order;
    }

    public void setId_order(Long id_order) {
        this.id_order = id_order;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public List<OrderPositionDto> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(List<OrderPositionDto> orderPositions) {
        this.orderPositions = orderPositions;
    }

    public void setMapOrderPositions(List<OrderPosition> ordersPositions) {
        for(OrderPosition orderPosition: ordersPositions){
            this.orderPositions.add(Mapper.map(orderPosition));
        }
    }

    public Double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }
}
