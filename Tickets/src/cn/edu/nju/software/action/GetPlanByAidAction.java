package cn.edu.nju.software.action;

import cn.edu.nju.software.models.*;
import cn.edu.nju.software.service.ActivityService;
import cn.edu.nju.software.service.TicketService;
import cn.edu.nju.software.service.VenueService;
import cn.edu.nju.software.utils.ActivityStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GetPlanByAidAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ActivityService activityService;
    private VenueService venueService;
    private TicketService ticketService;

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

    public TicketService getTicketService() {
        return ticketService;
    }

    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public String execute() throws Exception {
        String aid = request.getParameter("aid");
        Activity activity = activityService.findActivity(aid);
        Plan plan = activityService.findPlan(aid);
        Venue venue = venueService.findVenue(String.valueOf(plan.getVenueid()));
        List<Seat> seats = activityService.getSeats(aid);

        request.setAttribute("activity", activity);
        request.setAttribute("venue", venue);
        request.setAttribute("seats", seats);
        if(activity.getStatus() == ActivityStatus.PRESALE.ordinal()) {
            return "showActivityPresale";
        } else if(activity.getStatus() == ActivityStatus.ONSELLING.ordinal()) {
            List<Ticket> tickets = ticketService.getAllTicketsByAid(aid);
            request.setAttribute("tickets", tickets);
            return "showActivityOnselling";
        } else {
            return "showActivityPostsale";
        }
    }

}
