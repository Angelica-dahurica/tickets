package cn.edu.nju.software.action;

import cn.edu.nju.software.models.Venue;
import cn.edu.nju.software.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

@Controller
public class UpdateVenueAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    @Autowired
    private VenueService venueService;

    public VenueService getVenueService() {
        return venueService;
    }

    public void setVenueService(VenueService venueService) {
        this.venueService = venueService;
    }

    @Override
    public String execute() throws Exception {
        HttpSession session = request.getSession();
        Venue venue = (Venue) session.getAttribute("venue");
        String place = request.getParameter("place");
        String description = request.getParameter("description");
        int seatnum = Integer.parseInt(request.getParameter("seatnum"));
        venue.setPlace(place);
        venue.setDescription(description);
        venue.setSeatnum(seatnum);
        venue.setStatus(2);
        venueService.updateVenue(venue);
        request.setAttribute("venueid", venue.getVenueid());
        return null;
    }

}
