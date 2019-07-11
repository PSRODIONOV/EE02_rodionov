package fe.dto;

import be.entity.Flower;
import be.entity.Order;
import be.entity.OrderPosition;
import be.entity.User;

public class Mapper {

    public static UserDto map(User user){
        if(user != null) {
            return new UserDto(user);
        }
        return null;
    }
    public static OrderDto map(Order order){
        if(order != null) {
            return new OrderDto(order);
        }
        return null;
    }
    public static FlowerDto map(Flower flower){
        if(flower != null) {
            return new FlowerDto(flower);
        }
        return null;
    }
    public static OrderPositionDto map(OrderPosition orderPosition){
        if(orderPosition != null) {
            return new OrderPositionDto(orderPosition);
        }
        return null;
    }
}
