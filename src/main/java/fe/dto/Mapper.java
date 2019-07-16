package fe.dto;

import be.entity.Flower;
import be.entity.Order;
import be.entity.OrderPosition;
import be.entity.User;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static UserDto map(User user){
        if(user != null) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId_user());
            userDto.setLogin(user.getLogin());
            userDto.setLast_name(user.getLast_name());
            userDto.setFirst_name(user.getFirst_name());
            userDto.setSecond_name(user.getSecond_name());
            userDto.setAddress(user.getAddress());
            userDto.setPhone(user.getPhone());
            userDto.setWallet_score(user.getWallet_score());
            userDto.setDiscount(user.getDiscount());
            return userDto;
        }
        return null;
    }

    public static OrderDto map(Order order){
        if(order != null) {
            OrderDto orderDto = new OrderDto();
            orderDto.setId_order(order.getId_order());
            orderDto.setUserDto(map(order.getUser()));
            orderDto.setOrderPositions(mapOrderPositionsDto(order.getOrderPositions()));
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
            return flowerDto;
        }
        return null;
    }

    public static OrderPositionDto map(OrderPosition orderPosition){
        if(orderPosition != null) {
            OrderPositionDto orderPositionDto = new OrderPositionDto();
            //orderPositionDto.setOrderDto();
            orderPositionDto.setFlowerDto(map(orderPosition.getFlower()));
            orderPositionDto.setQuantity(orderPosition.getQuantity());
            return orderPositionDto;
        }
        return null;
    }

    public static User map(UserDto userDto){
        if(userDto != null){
            User user = new User();
            user.setId_user(userDto.getId());
            user.setWallet_score(userDto.getWallet_score());
            user.setDiscount(userDto.getDiscount());
            user.setAddress(userDto.getAddress());
            user.setFirst_name(userDto.getFirst_name());
            user.setSecond_name(userDto.getSecond_name());
            user.setPhone(userDto.getPhone());
            user.setLogin(userDto.getLogin());
            return user;
        }
        return null;
    }

    public static Flower map(FlowerDto flowerDto){
        if(flowerDto != null){
            Flower flower = new Flower();
            flower.setId_flower(flowerDto.getId_flower());
            flower.setName_flower(flowerDto.getName_flower());
            flower.setPrice(flowerDto.getPrice());
            return flower;
        }
        return null;
    }

    public static Order map(OrderDto orderDto){
        if(orderDto != null){
            Order order = new Order();
            order.setId_order(orderDto.getId_order());//передается null
            order.setOrderPositions(mapOrderPositions(orderDto.getOrderPositions()));
            order.setUser(map(orderDto.getUserDto()));
            return order;
        }
        return null;
    }

    public static OrderPosition map(OrderPositionDto orderPositionDto){
        if(orderPositionDto != null){
            OrderPosition orderPosition = new OrderPosition();
            orderPosition.setFlower(map(orderPositionDto.getFlowerDto()));
            orderPosition.setQuantity(orderPositionDto.getQuantity());
            orderPosition.setOrder(map(orderPositionDto.getOrderDto()));
            return orderPosition;
        }
        return null;
    }

    public static List<OrderPosition> mapOrderPositions(List<OrderPositionDto> orderPositionDtoList){
        if(orderPositionDtoList != null){
            List<OrderPosition> orderPositions = new ArrayList<>();
            for(OrderPositionDto orderPositionDto: orderPositionDtoList){
                orderPositions.add(map(orderPositionDto));
            }
            return orderPositions;
        }
        return null;
    }

    public static List<OrderPositionDto> mapOrderPositionsDto(List<OrderPosition> orderPositionList){
        if(orderPositionList != null){
            List<OrderPositionDto> orderPositionsDto = new ArrayList<>();
            for(OrderPosition orderPosition: orderPositionList){
                orderPositionsDto.add(map(orderPosition));
            }
            return orderPositionsDto;
        }
        return null;
    }

    public static List<FlowerDto> mapFlowers(List<Flower> flowers){
        if(flowers != null){
            List<FlowerDto> flowerDtoList = new ArrayList<>();
            for(Flower flower: flowers){
                flowerDtoList.add(Mapper.map(flower));
            }
            return flowerDtoList;
        }
        return null;
    }
    public static List<OrderDto> mapOrders(List<Order> orders){
        if(orders != null){
            List<OrderDto> orderDtoList = new ArrayList<>();
            for(Order order: orders){
                orderDtoList.add(Mapper.map(order));
            }
            return orderDtoList;
        }
        return null;
    }
}
