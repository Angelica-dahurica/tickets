package cn.edu.nju.software.service;

import cn.edu.nju.software.models.Activity;
import cn.edu.nju.software.models.Plan;
import cn.edu.nju.software.models.Seat;

import java.util.List;

public interface ActivityService {

    public void planActivity(Activity activity, String venueid, List<Seat> seats) throws Exception;

    public List<Activity> getAllActivities() throws Exception;

    public Plan findPlan(String aid) throws Exception;

    public List<Seat> getSeats(String aid) throws Exception;

    public Activity findActivity(String aid) throws Exception;

    public List<Activity> findActivitiesByType(String type) throws Exception;

    public String[] getType(Activity activity, String[] price) throws Exception;

    public double getDiff(int activityid) throws Exception;

    public List<Activity> getActivitiesByVenue(String venueid) throws Exception;

    public void updateActivity(Activity activity) throws Exception;
}
