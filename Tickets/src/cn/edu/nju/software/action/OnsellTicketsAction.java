package cn.edu.nju.software.action;

import cn.edu.nju.software.models.*;
import cn.edu.nju.software.service.ActivityService;
import cn.edu.nju.software.service.BankService;
import cn.edu.nju.software.service.TicketService;
import cn.edu.nju.software.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OnsellTicketsAction extends BaseAction{

    private static final long serialVersionUID = 1L;

    @Autowired
    private BankService bankService;
    private ActivityService activityService;
    private VenueService venueService;
    private TicketService ticketService;

    public BankService getBankService() {
        return bankService;
    }

    public void setBankService(BankService bankService) {
        this.bankService = bankService;
    }

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
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        Activity activity = (Activity) session.getAttribute("activity");

        if(ticketService.getAllTicketsByAM(String.valueOf(activity.getActivityid()), member.getEmail()).size() > 6) {
            return "notMoreThanSix";
        }

        List<Bank> banks = bankService.findBankCardsByEmail(member.getEmail());
        if(banks.size() == 0) {
            return "bindCard";
        } else {
            Venue venue = venueService.findVenue(String.valueOf(
                    activityService.findPlan(String.valueOf(activity.getActivityid())).getVenueid()));
            request.setAttribute("banks", banks);
            request.setAttribute("activity", activity);
            request.setAttribute("venue", venue);

            List<Ticket> tickets = ticketService.getAllTicketsByAM(String.valueOf(activity.getActivityid()), member.getEmail());
            request.setAttribute("tickets", tickets);
            return "chooseCard1";
        }

    }

}
