package cn.edu.nju.software.models;

import javax.persistence.*;

@Entity(name = "cn.edu.nju.software.models.Plan")
@Table(name = "plan")
@IdClass(value = PlanPrimaryKey.class)
public class Plan {

    @Id
    @Column(name = "venueid")
    private int venueid;

    @Id
    @Column(name = "activityid")
    private int activityid;

    @JoinColumn(name = "venueid")
    public int getVenueid() {
        return venueid;
    }

    public void setVenueid(int venueid) {
        this.venueid = venueid;
    }

    @JoinColumn(name = "activityid")
    public int getActivityid() {
        return activityid;
    }

    public void setActivityid(int activityid) {
        this.activityid = activityid;
    }

}
