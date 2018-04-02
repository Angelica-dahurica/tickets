package cn.edu.nju.software.models;


import javax.persistence.Column;
import javax.persistence.JoinColumn;
import java.io.Serializable;

public class CardOwnPrimaryKey implements Serializable{

    private static final long serialVersionUID = 1L;

    @Column(name = "email")
    private String email;

    @Column(name = "accountid")
    private int accountid;

    @JoinColumn(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JoinColumn(name = "accountid")
    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public int hashCode() {
        return getEmail().hashCode()*11 +
                String.valueOf(getAccountid()).hashCode();
    }

    public boolean equals(Object obj) {
        if(null == obj){
            return false;
        }
        if(this == obj){
            return true;
        }
        if(obj.getClass() == CardOwn.class){
            CardOwn cardOwn = (CardOwn) obj;
            if(cardOwn.getEmail().equals(getEmail()) &&
                    cardOwn.getAccountid() == getAccountid()){
                return true;
            }
        }
        return false;
    }

}
