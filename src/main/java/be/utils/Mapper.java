package be.utils;

import be.entity.Flower;
import be.entity.Order;
import be.entity.OrderPosition;
import be.entity.User;
import fe.dto.FlowerDto;
import fe.dto.OrderDto;
import fe.dto.OrderPositionDto;
import fe.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static UserDto map(User user) {
        if (user != null) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getIdUser());
            userDto.setRole(user.getRole());
            userDto.setLogin(user.getLogin());
            userDto.setLastName(user.getLastName());
            userDto.setFirstName(user.getFirstName());
            userDto.setSecondName(user.getSecondName());
            userDto.setAddress(user.getAddress());
            userDto.setPhone(user.getPhone());
            userDto.setWalletScore(user.getWalletScore());
            userDto.setDiscount(user.getDiscount());
            return userDto;
        }
        return null;
    }

    public static OrderDto map(Order order) {
        if (order != null) {
            OrderDto orderDto = new OrderDto();
            orderDto.setIdOrder(order.getIdOrder());
            orderDto.setUserDto(map(order.getUser()));
            orderDto.setOrderPositions(mapOrderPositionsDto(order.getOrderPositions()));
            orderDto.setTotalPrice(order.getTotalPrice());
            orderDto.setStatus(order.getStatus());
            orderDto.setDateCreate(order.getDateCreate());
            orderDto.setDateClose(order.getDateClose());
            return orderDto;
        }
        return null;
    }

    public static FlowerDto map(Flower flower) {
        if (flower != null) {
            FlowerDto flowerDto = new FlowerDto();
            flowerDto.setIdFlower(flower.getIdFlower());
            flowerDto.setNameFlower(flower.getNameFlower());
            flowerDto.setPrice(flower.getPrice());
            flowerDto.setQuantity(flower.getQuantity());
            return flowerDto;
        }
        return null;
    }

    public static OrderPositionDto map(OrderPosition orderPosition) {
        if (orderPosition != null) {
            OrderPositionDto orderPositionDto = new OrderPositionDto();
            orderPositionDto.setFlowerDto(map(orderPosition.getFlower()));
            orderPositionDto.setQuantity(orderPosition.getQuantity());
            return orderPositionDto;
        }
        return null;
    }

    public static User map(UserDto userDto) {
        if (userDto != null) {
            User user = new User();
            user.setIdUser(userDto.getId());
            user.setWalletScore(userDto.getWalletScore());
            user.setDiscount(userDto.getDiscount());
            user.setAddress(userDto.getAddress());
            user.setFirstName(userDto.getFirstName());
            user.setSecondName(userDto.getSecondName());
            user.setPhone(userDto.getPhone());
            user.setLogin(userDto.getLogin());
            user.setRole(userDto.getRole());
            return user;
        }
        return null;
    }

    public static Flower map(FlowerDto flowerDto) {
        if (flowerDto != null) {
            Flower flower = new Flower();
            flower.setIdFlower(flowerDto.getIdFlower());
            flower.setNameFlower(flowerDto.getNameFlower());
            flower.setPrice(flowerDto.getPrice());
            return flower;
        }
        return null;
    }

    public static Order map(OrderDto orderDto) {
        if (orderDto != null) {
            Order order = new Order();
            order.setIdOrder(orderDto.getIdOrder());
            order.setOrderPositions(mapOrderPositions(orderDto.getOrderPositions()));
            order.setUser(map(orderDto.getUserDto()));
            order.setTotalPrice(orderDto.getTotalPrice());
            return order;
        }
        return null;
    }

    public static OrderPosition map(OrderPositionDto orderPositionDto) {
        if (orderPositionDto != null) {
            OrderPosition orderPosition = new OrderPosition();
            orderPosition.setFlower(map(orderPositionDto.getFlowerDto()));
            orderPosition.setQuantity(orderPositionDto.getQuantity());
            return orderPosition;
        }
        return null;
    }

    public static List<OrderPosition> mapOrderPositions(List<OrderPositionDto> orderPositionDtoList) {
        if (orderPositionDtoList != null) {
            List<OrderPosition> orderPositions = new ArrayList<>();
            for (OrderPositionDto orderPositionDto : orderPositionDtoList) {
                orderPositions.add(map(orderPositionDto));
            }
            return orderPositions;
        }
        return null;
    }

    public static List<OrderPositionDto> mapOrderPositionsDto(List<OrderPosition> orderPositionList) {
        if (orderPositionList != null) {
            List<OrderPositionDto> orderPositionsDto = new ArrayList<>();
            for (OrderPosition orderPosition : orderPositionList) {
                orderPositionsDto.add(map(orderPosition));
            }
            return orderPositionsDto;
        }
        return null;
    }

    public static List<FlowerDto> mapFlowers(List<Flower> flowers) {
        if (flowers != null) {
            List<FlowerDto> flowerDtoList = new ArrayList<>();
            for (Flower flower : flowers) {
                flowerDtoList.add(Mapper.map(flower));
            }
            return flowerDtoList;
        }
        return null;
    }

    public static List<OrderDto> mapOrders(List<Order> orders) {
        if (orders != null) {
            List<OrderDto> orderDtoList = new ArrayList<>();
            for (Order order : orders) {
                orderDtoList.add(Mapper.map(order));
            }
            return orderDtoList;
        }
        return null;
    }
}
