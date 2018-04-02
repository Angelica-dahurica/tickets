package cn.edu.nju.software.dao;

import cn.edu.nju.software.models.Manager;

public interface ManagerDao {

    public Manager getManager() throws Exception;

    public void update(Manager manager) throws Exception;

}
