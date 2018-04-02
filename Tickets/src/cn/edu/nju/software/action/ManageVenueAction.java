package cn.edu.nju.software.action;

import cn.edu.nju.software.models.Venue;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

@Controller
public class ManageVenueAction extends BaseAction{

    private static final long serialVersionUID = 1L;

    @Override
    public String execute() throws Exception {
        HttpSession session = request.getSession();
        Venue venue = (Venue)session.getAttribute("venue");
        if(venue.getStatus() == 2){
            return "updatingVenue";
        }
        request.setAttribute("venue", venue);
        return "manageVenue";
    }

}
