package cn.edu.nju.software.models;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import java.io.Serializable;

public class PlanPrimaryKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "venueid")
    private int venueid;

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

    public int hashCode() {
        return String.valueOf(getActivityid()).hashCode()*11 +
                String.valueOf(getVenueid()).hashCode();
    }

    public boolean equals(Object obj) {
        if(null == obj){
            return false;
        }
        if(this == obj){
            return true;
        }
        if(obj.getClass() == Plan.class){
            Plan plan = (Plan) obj;
            if(plan.getActivityid() == getActivityid() &&
                    plan.getVenueid() == getVenueid()){
                return true;
            }
        }
        return false;
    }

}
