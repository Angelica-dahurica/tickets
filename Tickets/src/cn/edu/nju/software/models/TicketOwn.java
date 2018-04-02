package cn.edu.nju.software.models;

import javax.persistence.*;

@Entity(name = "cn.edu.nju.software.models.TicketOwn")
@Table(name = "own_ticket")
@IdClass(value = TicketOwnPrimaryKey.class)
public class TicketOwn {

    @Id
    @Column(name = "orderid")
    private int orderid;

    @Id
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

}
