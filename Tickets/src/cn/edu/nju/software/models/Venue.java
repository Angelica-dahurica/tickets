package cn.edu.nju.software.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "cn.edu.nju.software.models.Venue")
@Table(name = "venue")
public class Venue {

    @Id
    @Column(name = "venueid")
    private int venueid;

    @Column(name = "place")
    private String place;

    @Column(name = "description")
    private String description;

    @Column(name = "password")
    private String password;

    @Column(name = "seatnum")
    private int seatnum;

    @Column(name = "status")
    private int status;

    @Column(name = "bala")
    private double bala;

    public int getVenueid() {
        return venueid;
    }

    public void setVenueid(int venueid) {
        this.venueid = venueid;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSeatnum() {
        return seatnum;
    }

    public void setSeatnum(int seatnum) {
        this.seatnum = seatnum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getBala() {
        return bala;
    }

    public void setBala(double bala) {
        this.bala = bala;
    }
}
