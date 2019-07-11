package fe.dto;

import be.entity.Order;
import be.entity.User;

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

    }

    public UserDto(User user){
        this.login = user.getLogin();
        this.last_name = user.getLast_name();
        this.first_name = user.getFirst_name();
        this.second_name = user.getSecond_name();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.wallet_score = user.getWallet_score();
        this.discount = user.getDiscount();
        this.orders = new ArrayList<>();
        for(Order o: user.getOrders()){
            orders.add(new OrderDto(o));
        }
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
}
