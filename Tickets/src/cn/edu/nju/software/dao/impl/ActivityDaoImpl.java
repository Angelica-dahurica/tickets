package cn.edu.nju.software.dao.impl;

import cn.edu.nju.software.dao.ActivityDao;
import cn.edu.nju.software.dao.BaseDao;
import cn.edu.nju.software.models.Activity;
import cn.edu.nju.software.utils.ActivityStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ActivityDaoImpl implements ActivityDao {

    @Autowired
    private BaseDao baseDao;

    public BaseDao getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }


    @Override
    public void saveActivity(Activity activity) throws Exception {
        baseDao.save(activity);
    }

    @Override
    public Activity findActivity(String aid) throws Exception {
        return (Activity) baseDao.load(Activity.class, Integer.parseInt(aid));
    }

    @Override
    public List<Activity> getAllActivityList() throws Exception {
        List list = baseDao.getAllList(Activity.class);
        List<Activity> activities = new ArrayList<>();
        for(Object o : list){
            activities.add((Activity) o);
        }
        return activities;
    }

    @Override
    public List<Activity> getActivitiesByType(int type) throws Exception {
        List list = baseDao.query("from cn.edu.nju.software.models.Activity as a where a.type = " + type);
        List<Activity> activities = new ArrayList<>();
        for(Object o : list){
            activities.add((Activity) o);
        }
        return activities;
    }

    @Override
    public void update(Activity activity) throws Exception {
        baseDao.update(activity);
    }

    @Override
    public List<Activity> getActivitiesSetteled() throws Exception {
        List<Activity> tmp = getAllActivityList();
        List<Activity> activities = new ArrayList<>();
        for(Activity activity : tmp) {
            if(activity.getStatus() == ActivityStatus.SETTLED.ordinal()) {
                activities.add(activity);
            }
        }
        return activities;
    }

}
