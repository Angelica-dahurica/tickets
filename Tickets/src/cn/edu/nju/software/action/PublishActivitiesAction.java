package cn.edu.nju.software.action;

import cn.edu.nju.software.models.Activity;
import cn.edu.nju.software.models.Seat;
import cn.edu.nju.software.models.Venue;
import cn.edu.nju.software.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PublishActivitiesAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ActivityService activityService;

    public ActivityService getActivityService() {
        return activityService;
    }

    public void setActivityService(ActivityService activityService) {
        this.activityService = activityService;
    }

    private String[] seattype;

    private String[] num;

    private String[] price;

    public String[] getSeattype() {
        return seattype;
    }

    public void setSeattype(String[] seattype) {
        this.seattype = seattype;
    }

    public String[] getNum() {
        return num;
    }

    public void setNum(String[] num) {
        this.num = num;
    }

    public String[] getPrice() {
        return price;
    }

    public void setPrice(String[] price) {
        this.price = price;
    }

    @Override
    public String execute() throws Exception {
        HttpSession session = request.getSession();
        Venue venue = (Venue) session.getAttribute("venue");

        String name = request.getParameter("name");
        Date time = new SimpleDateFormat("dd MM yyyy - hh:mm").parse(request.getParameter("time"));
        int type = Integer.parseInt(request.getParameter("type"));
        String description = request.getParameter("description");
        int status = Integer.parseInt(request.getParameter("status"));
        Activity activity = new Activity();
        activity.setName(name);
        activity.setTime(new Timestamp(time.getTime()));
        activity.setType(type);
        activity.setDescription(description);
        activity.setStatus(status);

        List<Seat> seats = new ArrayList<>();
        for(int i = 0; i < seattype.length; i++) {
            int stype = 0;
            if(seattype[i].equals("看台")) {
                stype = 1;
            }
            seats.add(new Seat(stype, Double.parseDouble(price[i]), Integer.parseInt(num[i])));
        }

        activityService.planActivity(activity, String.valueOf(venue.getVenueid()), seats);
        return null;
    }


}
