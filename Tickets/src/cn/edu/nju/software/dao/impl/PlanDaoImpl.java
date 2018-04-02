package cn.edu.nju.software.dao.impl;

import cn.edu.nju.software.dao.BaseDao;
import cn.edu.nju.software.dao.PlanDao;
import cn.edu.nju.software.models.Activity;
import cn.edu.nju.software.models.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PlanDaoImpl implements PlanDao {

    @Autowired
    private BaseDao baseDao;

    public BaseDao getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }


    @Override
    public List<String> getAllActivityids(String venueid) throws Exception {
        List list = baseDao.query("from cn.edu.nju.software.models.Plan where venueid = " + venueid);
        List<String> activities = new ArrayList<>();
        for(Object o : list) {
            List tmp = baseDao.query("from cn.edu.nju.software.models.Activity where activityid = " + ((Plan) o).getActivityid());
            for(Object objecttmp : tmp){
                activities.add(String.valueOf(((Activity) objecttmp).getActivityid()));
            }
        }
        return activities;
    }

    @Override
    public void savePlan(Plan plan) throws Exception {
        baseDao.save(plan);
    }

    @Override
    public Plan findPlan(String aid) throws Exception {
        List list = baseDao.query("from cn.edu.nju.software.models.Plan as p where p.activityid = " + aid);
        return (Plan) list.get(0);
    }

}
