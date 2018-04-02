package cn.edu.nju.software.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class RecordPrimaryKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "orderid")
    private int orderid;

    @Column(name = "activityname")
    private String activityname;

    @Id
    @Column(name = "price")
    private double price;

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

    public int hashCode() {
        return String.valueOf(getPrice()).hashCode()*111 +
                String.valueOf(getOrderid()).hashCode()*11 + getActivityname().hashCode();
    }

    public boolean equals(Object obj) {
        if(null == obj){
            return false;
        }
        if(this == obj){
            return true;
        }
        if(obj.getClass() == Plan.class){
            Record record = (Record) obj;
            if(record.getPrice() == getPrice() &&
                    record.getOrderid() == getOrderid() &&
                    record.getActivityname().equals(getActivityname())){
                return true;
            }
        }
        return false;
    }
}
