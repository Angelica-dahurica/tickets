package cn.edu.nju.software.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class CouponPrimaryKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "email")
    private String email;

    @Id
    @Column(name = "quantity")
    private int quantity;

    @Id
    @Column(name = "price")
    private double price;

   public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


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

    public int hashCode() {
        return String.valueOf(getPrice()).hashCode()*111 +
                String.valueOf(getQuantity()).hashCode()*11 + getEmail().hashCode();
    }

    public boolean equals(Object obj) {
        if(null == obj){
            return false;
        }
        if(this == obj){
            return true;
        }
        if(obj.getClass() == Plan.class){
            Coupon coupon = (Coupon) obj;
            if(coupon.getPrice() == getPrice() &&
                    coupon.getQuantity() == getQuantity() &&
                    coupon.getEmail().equals(getEmail())){
                return true;
            }
        }
        return false;
    }
}
