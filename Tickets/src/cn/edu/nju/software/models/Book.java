package cn.edu.nju.software.models;

import javax.persistence.*;

@Entity(name = "cn.edu.nju.software.models.Book")
@Table(name = "book")
@IdClass(value = BookPrimaryKey.class)
public class Book {

    @Id
    @Column(name = "email")
    private String email;

    @Id
    @Column(name = "orderid")
    private int orderid;

    @JoinColumn(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JoinColumn(name = "orderid")
    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

}
