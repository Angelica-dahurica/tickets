package cn.edu.nju.software.dao.impl;

import cn.edu.nju.software.dao.BaseDao;
import cn.edu.nju.software.dao.SeatDao;
import cn.edu.nju.software.models.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SeatDaoImpl implements SeatDao {

    @Autowired
    private BaseDao baseDao;

    public BaseDao getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public void saveSeat(Seat seat) throws Exception {
        baseDao.save(seat);
    }

    @Override
    public Seat findSeat(String seatid) throws Exception {
        return (Seat) baseDao.load(Seat.class, Integer.parseInt(seatid));
    }

    @Override
    public void update(Seat seat) throws Exception {
        baseDao.update(seat);
    }

}
