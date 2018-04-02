package cn.edu.nju.software.models;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import java.io.Serializable;

public class SeatHavingPrimaryKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "activityid")
    private int activityid;

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

    public int hashCode() {
        return String.valueOf(getActivityid()).hashCode()*11 +
                String.valueOf(getSeatid()).hashCode();
    }

    public boolean equals(Object obj) {
        if(null == obj){
            return false;
        }
        if(this == obj){
            return true;
        }
        if(obj.getClass() == SeatHaving.class){
            SeatHaving seatHaving = (SeatHaving) obj;
            if(seatHaving.getActivityid() == getActivityid() &&
                    seatHaving.getSeatid() == getSeatid()){
                return true;
            }
        }
        return false;
    }

}
