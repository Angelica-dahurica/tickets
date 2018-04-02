package cn.edu.nju.software.dao;

import cn.edu.nju.software.models.Activity;

import java.util.List;

public interface ActivityDao {

    public void saveActivity(Activity activity)throws Exception;

    public Activity findActivity(String aid) throws Exception;

    public List<Activity> getAllActivityList()throws Exception;

    public List<Activity> getActivitiesByType(int type) throws Exception;

    public void update(Activity activity) throws Exception;

    public List<Activity> getActivitiesSetteled() throws Exception;
}
