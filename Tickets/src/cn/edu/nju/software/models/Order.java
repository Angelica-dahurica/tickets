package cn.edu.nju.software.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity(name = "cn.edu.nju.software.models.Order")
@Table(name = "[order]")
public class Order {

    @Id
    @Column(name = "orderid")
    private int orderid;

    @Column(name = "booktime")
    private Timestamp booktime;

    @Column(name = "status")
    private int status;

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public Timestamp getBooktime() {
        return booktime;
    }

    public void setBooktime(Timestamp booktime) {
        this.booktime = booktime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
