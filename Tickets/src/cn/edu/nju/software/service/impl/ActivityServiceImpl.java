package cn.edu.nju.software.service.impl;

import cn.edu.nju.software.dao.*;
import cn.edu.nju.software.models.*;
import cn.edu.nju.software.service.ActivityService;
import cn.edu.nju.software.utils.ActivityStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDao activityDao;
    private PlanDao planDao;
    private SeatDao seatDao;
    private SeatHavingDao seatHavingDao;
    private TicketDao ticketDao;

    public ActivityDao getActivityDao() {
        return activityDao;
    }

    public void setActivityDao(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    public PlanDao getPlanDao() {
        return planDao;
    }

    public void setPlanDao(PlanDao planDao) {
        this.planDao = planDao;
    }

    public SeatDao getSeatDao() {
        return seatDao;
    }

    public void setSeatDao(SeatDao seatDao) {
        this.seatDao = seatDao;
    }

    public SeatHavingDao getSeatHavingDao() {
        return seatHavingDao;
    }

    public void setSeatHavingDao(SeatHavingDao seatHavingDao) {
        this.seatHavingDao = seatHavingDao;
    }

    public TicketDao getTicketDao() {
        return ticketDao;
    }

    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    public void planActivity(Activity activity, String venueid, List<Seat> seats) throws Exception {
        activity.setActivityid(getNewAid());
        activityDao.saveActivity(activity);

        Plan plan = new Plan();
        plan.setActivityid(activity.getActivityid());
        plan.setVenueid(Integer.parseInt(venueid));
        planDao.savePlan(plan);

        SeatHaving seatHaving = new SeatHaving();
        seatHaving.setActivityid(activity.getActivityid());

        for(Seat seat: seats) {
            seat.setSeatid(getNewSid());
            seatDao.saveSeat(seat);

            seatHaving.setSeatid(seat.getSeatid());
            seatHavingDao.saveSeatHaving(seatHaving);
        }

        if (activity.getStatus() == ActivityStatus.ONSELLING.ordinal()) {  //售票中，可选座
            ticketDao.allocateTickets(seats, activity);
        }
    }

    @Override
    public List<Activity> getAllActivities() throws Exception {
        return activityDao.getAllActivityList();
    }

    @Override
    public Plan findPlan(String aid) throws Exception {
        return planDao.findPlan(aid);
    }

    @Override
    public List<Seat> getSeats(String aid) throws Exception {
        List<SeatHaving> seatHavings = seatHavingDao.findSeats(aid);
        List<Seat> seats = new ArrayList<>();
        for(SeatHaving seatHaving: seatHavings){
            seats.add(seatDao.findSeat(String.valueOf(seatHaving.getSeatid())));
        }
        return seats;
    }

    @Override
    public Activity findActivity(String aid) throws Exception {
        return activityDao.findActivity(aid);
    }

    @Override
    public List<Activity> findActivitiesByType(String type) throws Exception {
        List<Activity> activities = new ArrayList<>();
        switch (type){
            case "concert":
                activities = activityDao.getActivitiesByType(0);
                break;
            case "dancing":
                activities = activityDao.getActivitiesByType(1);
                break;
            case "drama":
                activities = activityDao.getActivitiesByType(2);
                break;
            case "sports":
                activities = activityDao.getActivitiesByType(3);
                break;
            default:
                break;
        }
        return activities;
    }

    @Override
    public String[] getType(Activity activity, String[] price) throws Exception {
        List<Seat> seats = getSeats(String.valueOf(activity.getActivityid()));
        String[] type = new String[price.length];
        for(int i = 0; i < price.length; i++){
            for(Seat seat: seats) {
                if (Double.parseDouble(price[i]) == seat.getPrice()) {
                    type[i] = seat.getType() == 0 ? "内场" : "看台";
                    break;
                }
            }
        }
        return type;
    }

    @Override
    public double getDiff(int activityid) throws Exception {
        Activity activity = findActivity(String.valueOf(activityid));
        long t1 = System.currentTimeMillis();
        long t2 = activity.getTime().getTime();
        if((t2 - t1) /1000 > 30 * 24 * 60 * 60){   //距离演出还有一个多月
            return 0.95;
        } else if((t2 - t1) /1000 > 14 * 24 * 60 * 60) {    //距离演出还有两星期
            return 0.8;
        }
        return 0.6;
    }

    @Override
    public List<Activity> getActivitiesByVenue(String venueid) throws Exception {
        List<Activity> activities = activityDao.getAllActivityList();
        List<Activity> list = new ArrayList<>();
        for(Activity activity: activities) {
            if(planDao.findPlan(String.valueOf(activity.getActivityid())).getVenueid() == Integer.parseInt(venueid)) {
                list.add(activity);
            }
        }
        return list;
    }

    @Override
    public void updateActivity(Activity activity) throws Exception{
        activityDao.update(activity);
    }

    private int getNewSid() throws Exception{
        while(true){
            int randNum = (int)(Math.random()*9999999)+1;
            String seatid = String.format("%09d", randNum);
            if(seatDao.findSeat(seatid) == null){
                return Integer.parseInt(seatid);
            }
        }
    }

    private int getNewAid() throws Exception{
        while(true){
            int randNum = (int)(Math.random()*9999999)+1;
            String aid = String.format("%09d", randNum);
            if(activityDao.findActivity(aid) == null){
                return Integer.parseInt(aid);
            }
        }
    }

}
