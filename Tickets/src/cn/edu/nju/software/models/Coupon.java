package cn.edu.nju.software.models;

import javax.persistence.*;

@Entity(name = "cn.edu.nju.software.models.Coupon")
@Table(name = "coupon")
@IdClass(value = CouponPrimaryKey.class)
public class Coupon {

    @Id
    @Column(name = "email")
    private String email;

    @Id
    @Column(name = "quantity")
    private int quantity;

    @Id
    @Column(name = "price")
    private double price;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
