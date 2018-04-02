package cn.edu.nju.software.dao.impl;

import cn.edu.nju.software.dao.BaseDao;
import cn.edu.nju.software.dao.ManagerDao;
import cn.edu.nju.software.models.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ManagerDaoImpl implements ManagerDao {

    @Autowired
    private BaseDao baseDao;

    public BaseDao getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public Manager getManager() throws Exception {
        return (Manager) baseDao.load(Manager.class, "admin");
    }

    @Override
    public void update(Manager manager) throws Exception {
        baseDao.update(manager);
    }
}
