package cn.edu.nju.software.models;

import javax.persistence.*;

@Entity(name = "cn.edu.nju.software.models.Presale")
@Table(name = "presale")
@IdClass(value = PresalePrimaryKey.class)
public class Presale {

    @Id
    @Column(name = "orderid")
    private int orderid;

    @Column(name = "email")
    private String email;

    @Column(name = "activityid")
    private int activityid;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "type")
    private String type;

    @Id
    @Column(name = "price")
    private String price;

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getActivityid() {
        return activityid;
    }

    public void setActivityid(int activityid) {
        this.activityid = activityid;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
