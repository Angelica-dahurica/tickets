package cn.edu.nju.software.dao.impl;

import cn.edu.nju.software.dao.BaseDao;
import cn.edu.nju.software.dao.SeatHavingDao;
import cn.edu.nju.software.models.SeatHaving;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SeatHavingDaoImpl implements SeatHavingDao{

    @Autowired
    private BaseDao baseDao;

    public BaseDao getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public void saveSeatHaving(SeatHaving seatHaving) throws Exception {
        baseDao.save(seatHaving);
    }

    @Override
    public List<SeatHaving> findSeats(String aid) throws Exception {
        List list = baseDao.query("from cn.edu.nju.software.models.SeatHaving as sh where sh.activityid = " + aid);
        List<SeatHaving> seatHavings = new ArrayList<>();
        for(Object o : list){
            seatHavings.add((SeatHaving) o);
        }
        return seatHavings;
    }
}
