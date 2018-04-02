package cn.edu.nju.software.action;

import cn.edu.nju.software.models.Manager;
import cn.edu.nju.software.models.Venue;
import cn.edu.nju.software.service.MemberService;
import cn.edu.nju.software.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LogInManagerAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    @Autowired
    private MemberService memberService;
    private VenueService venueService;

    public MemberService getMemberService() {
        return memberService;
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
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

        Manager manager = memberService.getManager();

        if (manager!= null && id.equals(manager.getName()) && password.equals(manager.getPassword())) {
            List<Venue> venueList = venueService.getAllVenues();
            request.setAttribute("venueList", venueList);
            return "managerPage";
        } else {
            return "logInError";
        }
    }

}
