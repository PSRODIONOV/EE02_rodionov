package be.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cust")
    @SequenceGenerator(name = "cust", sequenceName = "seq_user")
    private Long id_user;
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "PASSWORD")
    private String password;
    @Column (name = "LAST_NAME")
    private String last_name;
    @Column (name = "FIRST_NAME")
    private String first_name;
    @Column (name = "SECOND_NAME")
    private String second_name;
    @Column (name = "ADDRESS")
    private String address;
    @Column (name = "PHONE")
    private String phone;
    @Column (name = "WALLET_SCORE")
    private Double wallet_score;
    @Column (name = "DISCOUNT")
    private Integer discount;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Order> orders;

    public User(){}

    public User(String login, String password, String address){
        this.login = login;
        this.password = password;
        this.address = address;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<Order> getOrders() {
        return orders;
    }

}
