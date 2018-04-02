package cn.edu.nju.software.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity(name = "cn.edu.nju.software.models.Activity")
@Table(name = "activity")
public class Activity {

    @Id
    @Column(name = "activityid")
    private int activityid;

    @Column(name = "name")
    private String name;

    @Column(name = "time")
    private Timestamp time;

    @Column(name = "type")
    private int type;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private int status;

    public int getActivityid() {
        return activityid;
    }

    public void setActivityid(int activityid) {
        this.activityid = activityid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
