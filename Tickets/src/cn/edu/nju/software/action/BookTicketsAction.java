package cn.edu.nju.software.action;

import cn.edu.nju.software.models.*;
import cn.edu.nju.software.service.ActivityService;
import cn.edu.nju.software.service.BankService;
import cn.edu.nju.software.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BookTicketsAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    @Autowired
    private BankService bankService;
    private ActivityService activityService;
    private VenueService venueService;

    private String[] price;
    private String[] quantity;

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

    public String[] getPrice() {
        return price;
    }

    public void setPrice(String[] price) {
        this.price = price;
    }

    public String[] getQuantity() {
        return quantity;
    }

    public void setQuantity(String[] quantity) {
        this.quantity = quantity;
    }

    @Override
    public String execute() throws Exception {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        List<Bank> banks = bankService.findBankCardsByEmail(member.getEmail());
        if(banks.size() == 0) {
            return "bindCard";
        } else {
            Activity activity = (Activity) session.getAttribute("activity");
            Venue venue = venueService.findVenue(String.valueOf(
                    activityService.findPlan(String.valueOf(activity.getActivityid())).getVenueid()));
            String[] type = activityService.getType(activity, price);
            request.setAttribute("banks", banks);
            request.setAttribute("price", price);
            request.setAttribute("quantity", quantity);
            request.setAttribute("activity", activity);
            request.setAttribute("venue", venue);
            request.setAttribute("type", type);
            return "chooseCard";
        }

    }

}
