package cn.edu.nju.software.models;

import javax.persistence.*;

@Entity(name = "cn.edu.nju.software.models.SeatHaving")
@Table(name = "has_seat")
@IdClass(value = SeatHavingPrimaryKey.class)
public class SeatHaving {

    @Id
    @Column(name = "activityid")
    private int activityid;

    @Id
    @Column(name = "seatid")
    private int seatid;

    @JoinColumn(name = "activityid")
    public int getActivityid() {
        return activityid;
    }

    public void setActivityid(int activityid) {
        this.activityid = activityid;
    }

    @JoinColumn(name = "seatid")
    public int getSeatid() {
        return seatid;
    }

    public void setSeatid(int seatid) {
        this.seatid = seatid;
    }

}
