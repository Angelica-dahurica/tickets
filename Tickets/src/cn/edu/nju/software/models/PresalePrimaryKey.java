package cn.edu.nju.software.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class PresalePrimaryKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "orderid")
    private int orderid;

    @Id
    @Column(name = "price")
    private String price;

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int hashCode() {
        return getPrice().hashCode()*11 +
                String.valueOf(getOrderid()).hashCode();
    }

    public boolean equals(Object obj) {
        if(null == obj){
            return false;
        }
        if(this == obj){
            return true;
        }
        if(obj.getClass() == Plan.class){
            Presale presale = (Presale) obj;
            if(presale.getPrice().equals(getPrice()) &&
                    presale.getOrderid() == getOrderid()){
                return true;
            }
        }
        return false;
    }
}
