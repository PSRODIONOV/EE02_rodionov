package be.entity;

import be.utils.enums.UserType;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@XmlRootElement
@Entity
@Table(name = "USERS")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cust")
    @SequenceGenerator(name = "cust", sequenceName = "seq_user", allocationSize = 1, initialValue = 1)
    @Column(name = "ID_USER")
    @XmlElement(name = "idUser")
    private Long idUser;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    @XmlElement(name = "role")
    private UserType role;

    @Column(name = "LOGIN")
    @XmlElement(name = "login")
    private String login;

    @Column(name = "PASSWORD")
    @XmlElement(name = "password")
    private String password;

    @Column(name = "LAST_NAME")
    @XmlElement(name = "lastName")
    private String lastName;

    @Column(name = "FIRST_NAME")
    @XmlElement(name = "firstName")
    private String firstName;

    @Column(name = "SECOND_NAME")
    @XmlElement(name = "secondName")
    private String secondName;

    @Column(name = "ADDRESS")
    @XmlElement(name = "address")
    private String address;

    @Column(name = "PHONE")
    @XmlElement(name = "phone")
    private String phone;

    @Column(name = "WALLET_SCORE")
    @XmlElement(name = "walletScore")
    private BigDecimal walletScore;

    @Column(name = "DISCOUNT")
    @XmlElement(name = "discount")
    private Integer discount;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Order> orders;

    public User() {
    }

    public User(String login, String password, String address) {
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

    public UserType getRole() {
        return role;
    }

    public void setRole(UserType role) {
        this.role = role;
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

    public BigDecimal getWalletScore() {
        return walletScore;
    }

    public void setWalletScore(BigDecimal walletScore) {
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
