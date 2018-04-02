package cn.edu.nju.software.models;

import javax.persistence.*;

@Entity(name = "cn.edu.nju.software.models.Seat")
@Table(name = "seat")
public class Seat {

    @Id
    @Column(name = "seatid")
    private int seatid;

    @Column(name = "type")
    private int type;

    @Column(name = "price")
    private double price;

    @Column(name = "num")
    private int num;

    public Seat() {
    }

    public Seat(int type, double price, int num) {
        this.type = type;
        this.price = price;
        this.num = num;
    }

    public int getSeatid() {
        return seatid;
    }

    public void setSeatid(int seatid) {
        this.seatid = seatid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}
