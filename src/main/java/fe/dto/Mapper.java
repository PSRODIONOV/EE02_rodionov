package fe.dto;

import be.entity.Flower;
import be.entity.Order;
import be.entity.OrderPosition;
import be.entity.User;

public class Mapper {

    public static UserDto map(User user){
        if(user != null) {
            UserDto userDto = new UserDto();
            userDto.setLogin(user.getLogin());
            userDto.setLast_name(user.getLast_name());
            userDto.setFirst_name(user.getFirst_name());
            userDto.setSecond_name(user.getSecond_name());
            userDto.setAddress(user.getAddress());
            userDto.setPhone(user.getPhone());
            userDto.setWallet_score(user.getWallet_score());
            userDto.setDiscount(user.getDiscount());
            userDto.setMapOrders(user.getOrders());
            return userDto;
        }
        return null;
    }
    public static OrderDto map(Order order){
        if(order != null) {
            OrderDto orderDto = new OrderDto();
            orderDto.setId_order(order.getId_order());
            orderDto.setId_user(order.getUser().getId_user());
            orderDto.setMapOrderPositions(order.getOrderPositions());
            return orderDto;
        }
        return null;
    }
    public static FlowerDto map(Flower flower){
        if(flower != null) {
            FlowerDto flowerDto = new FlowerDto();
            flowerDto.setId_flower(flower.getId_flower());
            flowerDto.setName_flower(flower.getName_flower());
            flowerDto.setPrice(flower.getPrice());
            flowerDto.setQuantity(flower.getQuantity());
            return flowerDto;
        }
        return null;
    }
    public static OrderPositionDto map(OrderPosition orderPosition){
        if(orderPosition != null) {
            OrderPositionDto orderPositionDto = new OrderPositionDto();
            orderPositionDto.setId_flower(orderPosition.getFlower().getId_flower());
            orderPositionDto.setPrice(orderPosition.getFlower().getPrice());
            orderPositionDto.setQuantity(orderPosition.getQuantity());
            return orderPositionDto;
        }
        return null;
    }
}
