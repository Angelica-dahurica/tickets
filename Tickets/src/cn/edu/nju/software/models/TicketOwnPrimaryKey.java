package cn.edu.nju.software.models;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import java.io.Serializable;

public class TicketOwnPrimaryKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "orderid")
    private int orderid;

    @Column(name = "ticketid")
    private int ticketid;

    @JoinColumn(name = "orderid")
    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    @JoinColumn(name = "ticketid")
    public int getTicketid() {
        return ticketid;
    }

    public void setTicketid(int ticketid) {
        this.ticketid = ticketid;
    }

    public int hashCode() {
        return String.valueOf(getOrderid()).hashCode()*11 +
                String.valueOf(getTicketid()).hashCode();
    }

    public boolean equals(Object obj) {
        if(null == obj){
            return false;
        }
        if(this == obj){
            return true;
        }
        if(obj.getClass() == TicketOwn.class){
            TicketOwn ticketOwn = (TicketOwn) obj;
            if(ticketOwn.getOrderid() == getOrderid() &&
                    ticketOwn.getTicketid() == getTicketid()){
                return true;
            }
        }
        return false;
    }

}
