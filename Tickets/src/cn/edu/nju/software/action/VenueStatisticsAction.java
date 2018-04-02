package cn.edu.nju.software.action;

import cn.edu.nju.software.models.Activity;
import cn.edu.nju.software.models.Venue;
import cn.edu.nju.software.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class VenueStatisticsAction extends BaseAction {

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
        HttpSession session = request.getSession();
        Venue venue = (Venue) session.getAttribute("venue");
        List<Activity> activities = activityService.getActivitiesByVenue(String.valueOf(venue.getVenueid()));
        request.setAttribute("activities", activities);
        return "venuePage";
    }

}
