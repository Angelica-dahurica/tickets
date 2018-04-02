package cn.edu.nju.software.action;

import cn.edu.nju.software.models.Venue;
import org.springframework.stereotype.Controller;

@Controller
public class PlanActivitiesAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    @Override
    public String execute() throws Exception {
        Venue venue = (Venue) request.getAttribute("venue");
        request.setAttribute("venue", venue);
        return "planActivities";
    }

}
