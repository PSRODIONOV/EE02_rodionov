package fe.dto;

import be.entity.Order;
import be.entity.OrderPosition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderDto {

    private Long id_order;
    private Long id_user;
    private List<OrderPositionDto> orderPosition;
    private Double total_price= 0.0;

    public OrderDto(Order order){
        this.id_order = order.getId_order();
        this.id_user = order.getUser().getId_user();
        this.orderPosition = new ArrayList<>();
        for (OrderPosition op: order.getOrderPositions()) {
            this.orderPosition.add(new OrderPositionDto(op));
            this.total_price += op.getFlower().getPrice() * op.getQuantity();
        }
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

    public List<OrderPositionDto> getOrderPosition() {
        return orderPosition;
    }

    public void setOrderPosition(List<OrderPositionDto> orderPosition) {
        this.orderPosition = orderPosition;
    }

    public Double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }
}
