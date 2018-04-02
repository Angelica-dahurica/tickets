package cn.edu.nju.software.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "cn.edu.nju.software.models.Ticket")
@Table(name = "ticket")
public class Ticket {

    @Id
    @Column(name = "ticketid")
    private int ticketid;

    @Column(name = "activityid")
    private int activityid;

    @Column(name = "seattype")
    private int seattype;

    @Column(name = "row")
    private int row;

    @Column(name = "col")
    private int col;

    @Column(name = "price")
    private double price;

    @Column(name = "status")
    private int status;

    @Column(name = "locktime")
    private Timestamp locktime;

    @Column(name = "lockperson")
    private String lockperson;

    public int getTicketid() {
        return ticketid;
    }

    public void setTicketid(int ticketid) {
        this.ticketid = ticketid;
    }

    @JoinColumn(name = "activityid")
    public int getActivityid() {
        return activityid;
    }

    public void setActivityid(int activityid) {
        this.activityid = activityid;
    }

    public int getSeattype() {
        return seattype;
    }

    public void setSeattype(int seattype) {
        this.seattype = seattype;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getLocktime() {
        return locktime;
    }

    public void setLocktime(Timestamp locktime) {
        this.locktime = locktime;
    }

    public String getLockperson() {
        return lockperson;
    }

    public void setLockperson(String lockperson) {
        this.lockperson = lockperson;
    }
}
