package fe.dto;

import be.utils.enums.OrderStatus;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
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
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        if (dateCreate != null) {
            this.dateCreate = dateCreate.toString();
        } else {
            this.dateCreate = "--.--.----";
        }
    }

    public String getDateClose() {
        return dateClose;
    }

    public void setDateClose(LocalDateTime dateClose) {
        if (dateClose != null) {
            this.dateClose = dateClose.toString();
        } else {
            this.dateClose = "--.--.----";
        }
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
