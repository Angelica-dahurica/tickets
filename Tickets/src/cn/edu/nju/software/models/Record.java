package cn.edu.nju.software.models;

import javax.persistence.*;

@Entity(name = "cn.edu.nju.software.models.Record")
@Table(name = "record")
@IdClass(value = RecordPrimaryKey.class)
public class Record {

    @Id
    @Column(name = "orderid")
    private int orderid;

    @Column(name = "activityid")
    private int activityid;

    @Column(name = "activityname")
    private String activityname;

    @Id
    @Column(name = "price")
    private double price;

    @Id
    @Column(name = "operate")
    private int operate;

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getActivityname() {
        return activityname;
    }

    public void setActivityname(String activityname) {
        this.activityname = activityname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOperate() {
        return operate;
    }

    public void setOperate(int operate) {
        this.operate = operate;
    }

    public int getActivityid() {
        return activityid;
    }

    public void setActivityid(int activityid) {
        this.activityid = activityid;
    }
}
