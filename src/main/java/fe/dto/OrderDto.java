package fe.dto;

import be.utils.enums.OrderStatus;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class OrderDto {

    private Long idOrder;
    private UserDto user;
    private List<OrderPositionDto> orderPositions;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private String dateCreate;
    private String dateClose;

    public OrderDto() {
        this.orderPositions = new ArrayList<>();
        this.totalPrice = new BigDecimal(0);
        this.status = OrderStatus.CREATED;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public List<OrderPositionDto> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(List<OrderPositionDto> orderPositions) {
        this.orderPositions = orderPositions;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getDateCreate() {
        if(dateCreate == null){
            return "YY-MM-DD";
        }
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getDateClose() {
        if(dateClose == null){
            return "YY-MM-DD";
        }
        return dateClose;
    }

    public void setDateClose(String dateClose) {
        this.dateClose = dateClose;
    }

    public void computePrice() {
        BigDecimal price = BigDecimal.ZERO;
        for (OrderPositionDto orderPositionDto : orderPositions) {
            price = price.add(orderPositionDto.getPrice());
        }
        this.totalPrice = price.multiply(new BigDecimal(100).subtract(new BigDecimal(user.getDiscount()))
                .divide(new BigDecimal(100))).setScale(2, RoundingMode.HALF_DOWN);
    }

}
