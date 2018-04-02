package cn.edu.nju.software.action;

import cn.edu.nju.software.models.Activity;
import cn.edu.nju.software.service.ActivityService;
import cn.edu.nju.software.utils.ActivityStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SettleFinanceAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ActivityService activityService;

    public ActivityService getActivityService() {
        return activityService;
    }

    public void setActivityService(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public String execute() throws Exception {
        List<Activity> tmp = activityService.getAllActivities();
        List<Activity> activities = new ArrayList<>();
        for(Activity activity: tmp) {
            if(activity.getStatus() == ActivityStatus.POSTSALE.ordinal()) {
                activities.add(activity);
            }
        }

        request.setAttribute("activities", activities);
        return "settleFinance";
    }

}
