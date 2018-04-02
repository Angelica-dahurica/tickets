package cn.edu.nju.software.action;

import cn.edu.nju.software.models.*;
import cn.edu.nju.software.service.ActivityService;
import cn.edu.nju.software.service.MemberService;
import cn.edu.nju.software.service.RecordService;
import cn.edu.nju.software.service.VenueService;
import cn.edu.nju.software.utils.ActivityStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SettleAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    @Autowired
    private RecordService recordService;
    private ActivityService activityService;
    private VenueService venueService;
    private MemberService memberService;

    public RecordService getRecordService() {
        return recordService;
    }

    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
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

    public MemberService getMemberService() {
        return memberService;
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public String execute() throws Exception {
        String activityid = request.getParameter("activityid");

        List<Record> recordList = recordService.getAllByAid(activityid);
        double price = 0;
        for(Record record : recordList) {
            price += record.getPrice();
        }

        Plan plan = activityService.findPlan(activityid);
        Venue venue = venueService.findVenue(String.valueOf(plan.getVenueid()));
        venue.setBala(venue.getBala()-price * 0.8);
        venueService.updateVenue(venue);

        Manager manager = memberService.getManager();
        manager.setBala(manager.getBala()-price * 0.8);
        memberService.updateManager(manager);

        Activity activity = activityService.findActivity(activityid);
        activity.setStatus(ActivityStatus.SETTLED.ordinal());
        activityService.updateActivity(activity);
        return null;
    }
}
