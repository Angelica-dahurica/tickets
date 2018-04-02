package cn.edu.nju.software.action;

import cn.edu.nju.software.models.Activity;
import cn.edu.nju.software.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ShowActivitiesAction extends BaseAction {

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
        List<Activity> activityList = activityService.getAllActivities();
        request.setAttribute("activityList", activityList);
        return "showActivities";
    }

}
