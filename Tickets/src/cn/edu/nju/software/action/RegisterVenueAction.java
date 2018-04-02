package cn.edu.nju.software.action;

import cn.edu.nju.software.models.Venue;
import cn.edu.nju.software.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RegisterVenueAction extends BaseAction{

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
        String place = request.getParameter("place");
        String description = request.getParameter("description");
        String password = request.getParameter("password");
        int seatnum = Integer.parseInt(request.getParameter("seatnum"));
        Venue venue = new Venue();
        venue.setPlace(place);
        venue.setDescription(description);
        venue.setPassword(password);
        venue.setSeatnum(seatnum);
        int venueid = venueService.register(venue);
        request.setAttribute("venueid", venueid);
        return "registerVenueSuccess";
    }

}
