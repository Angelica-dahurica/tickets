package cn.edu.nju.software.models;

import java.sql.Timestamp;

public class OrderInfo {

    private int orderid;

    private Timestamp booktime;

    private int status;

    private int activityid;

    private String activityname;

    private String quantity;

    private String type;

    private String price;

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

    public int getActivityid() {
        return activityid;
    }

    public void setActivityid(int activityid) {
        this.activityid = activityid;
    }

    public String getActivityname() {
        return activityname;
    }

    public void setActivityname(String activityname) {
        this.activityname = activityname;
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
