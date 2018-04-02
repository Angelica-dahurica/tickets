package cn.edu.nju.software.dao.impl;

import cn.edu.nju.software.dao.BaseDao;
import cn.edu.nju.software.dao.PresaleDao;
import cn.edu.nju.software.models.Presale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PresaleDaoImpl implements PresaleDao {

    @Autowired
    private BaseDao baseDao;

    public BaseDao getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public void save(Presale presale) throws Exception {
        baseDao.save(presale);
    }

    @Override
    public List<Presale> find(int orderid) throws Exception {
        List list = baseDao.query("from cn.edu.nju.software.models.Presale as p where p.orderid = " + orderid);
        List<Presale> presales = new ArrayList<>();
        for(Object o: list) {
            presales.add((Presale) o);
        }
        return presales;
    }
}
