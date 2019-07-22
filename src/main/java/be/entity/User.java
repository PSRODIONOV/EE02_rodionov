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
    @Column(name = "ID_USER")
    private Long idUser;
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "PASSWORD")
    private String password;
    @Column (name = "LAST_NAME")
    private String lastName;
    @Column (name = "FIRST_NAME")
    private String firstName;
    @Column (name = "SECOND_NAME")
    private String secondName;
    @Column (name = "ADDRESS")
    private String address;
    @Column (name = "PHONE")
    private String phone;
    @Column (name = "WALLET_SCORE")
    private Double walletScore;
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

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
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

    public Double getWalletScore() {
        return walletScore;
    }

    public void setWalletScore(Double walletScore) {
        this.walletScore = walletScore;
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
