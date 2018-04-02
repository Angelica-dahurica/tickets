package cn.edu.nju.software.action;

import cn.edu.nju.software.models.Activity;
import cn.edu.nju.software.models.Venue;
import cn.edu.nju.software.service.ActivityService;
import cn.edu.nju.software.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LogInVenueAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    @Autowired
    private VenueService venueService;
    private ActivityService activityService;

    public ActivityService getActivityService() {
        return activityService;
    }

    public void setActivityService(ActivityService activityService) {
        this.activityService = activityService;
    }


    public VenueService getVenueService() {
        return venueService;
    }

    public void setVenueService(VenueService venueService) {
        this.venueService = venueService;
    }

    @Override
    public String execute() throws Exception {
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        Venue venue = venueService.findVenue(id);
        if (venue != null) {
            if (venue.getStatus() == 0) {
                return "inChecking";
            } else if (venue.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("venue", venue);
                session.setAttribute("type", "venue");

                List<Activity> activities = activityService.getActivitiesByVenue(String.valueOf(venue.getVenueid()));
                request.setAttribute("activities", activities);
                return "venuePage";
            }
        }
        return "logInError";
    }

}
