package cn.edu.nju.software.dao.impl;

import cn.edu.nju.software.dao.BaseDao;
import cn.edu.nju.software.dao.TicketOwnDao;
import cn.edu.nju.software.models.TicketOwn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketOwnDaoImpl implements TicketOwnDao {

    @Autowired
    private BaseDao baseDao;

    public BaseDao getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public List<String> getOrderids(String ticketid) throws Exception {
        List list = baseDao.query("from cn.edu.nju.software.models.TicketOwn as town where town.ticketid = " + ticketid);
        List<String> orderids = new ArrayList<>();
        for(Object o : list){
            orderids.add(String.valueOf(((TicketOwn) o).getOrderid()));
        }
        return orderids;
    }

    @Override
    public void save(TicketOwn ticketOwn) throws Exception {
        baseDao.save(ticketOwn);
    }

    @Override
    public void delete(TicketOwn ticketOwn) throws Exception {
        List list = baseDao.query("from cn.edu.nju.software.models.TicketOwn as town where town.ticketid = " + ticketOwn.getTicketid()
        + " and town.orderid = " + ticketOwn.getOrderid());
        baseDao.delete(list.get(0));
    }

    @Override
    public List<TicketOwn> findByOid(String oid) throws Exception {
        List list = baseDao.query("from cn.edu.nju.software.models.TicketOwn as town where town.orderid = " + oid);
        List<TicketOwn> ticketOwns = new ArrayList<>();
        for(Object o : list){
            ticketOwns.add(((TicketOwn) o));
        }
        return ticketOwns;
    }

}
