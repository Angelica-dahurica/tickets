package cn.edu.nju.software.models;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import java.io.Serializable;

public class BookPrimaryKey implements Serializable{

    private static final long serialVersionUID = 1L;

    @Column(name = "email")
    private String email;

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

    public int hashCode() {
        return getEmail().hashCode()*11 +
                String.valueOf(getOrderid()).hashCode();
    }

    public boolean equals(Object obj) {
        if(null == obj){
            return false;
        }
        if(this == obj){
            return true;
        }
        if(obj.getClass() == Book.class){
            Book b = (Book) obj;
            if(b.getEmail().equals(getEmail()) &&
                    b.getOrderid() == getOrderid()){
                return true;
            }
        }
        return false;
    }

}
