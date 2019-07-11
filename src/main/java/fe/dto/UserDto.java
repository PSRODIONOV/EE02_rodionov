package fe.dto;

import be.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class UserDto {

    private String login;
    private String last_name;
    private String first_name;
    private String second_name;
    private String address;
    private String phone;
    private Double wallet_score;
    private Integer discount;
    private List<OrderDto> orders;

    public UserDto(){
        orders = new ArrayList<>();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getWallet_score() {
        return wallet_score;
    }

    public void setWallet_score(Double wallet_score) {
        this.wallet_score = wallet_score;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public List<OrderDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDto> orders) {
        this.orders = orders;
    }

    public void setMapOrders(List<Order> orders) {
        for(Order order: orders){
            this.orders.add(Mapper.map(order));
        }
    }

}
