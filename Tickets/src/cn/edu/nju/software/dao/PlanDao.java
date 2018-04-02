package cn.edu.nju.software.dao;

import cn.edu.nju.software.models.Plan;

import java.util.List;

public interface PlanDao {

    public List<String> getAllActivityids(String venueid) throws Exception;

    public void savePlan(Plan plan) throws Exception;

    public Plan findPlan(String aid) throws Exception;

}
