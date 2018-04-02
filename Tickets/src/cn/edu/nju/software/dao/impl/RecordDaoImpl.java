package cn.edu.nju.software.dao.impl;

import cn.edu.nju.software.dao.BaseDao;
import cn.edu.nju.software.dao.RecordDao;
import cn.edu.nju.software.models.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RecordDaoImpl implements RecordDao {

    @Autowired
    private BaseDao baseDao;

    public BaseDao getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }


    @Override
    public void save(Record record) throws Exception {
        baseDao.save(record);
    }

    @Override
    public List<Record> find(int orderid) throws Exception {
        List list = baseDao.query("from cn.edu.nju.software.models.Record as r where r.orderid = " + orderid);
        List<Record> records = new ArrayList<>();
        for(Object o : list){
            records.add((Record) o);
        }
        return records;
    }

    @Override
    public List<Record> findByAid(String aid) throws Exception {
        List list = baseDao.query("from cn.edu.nju.software.models.Record as r where r.activityid = " + aid);
        List<Record> records = new ArrayList<>();
        for(Object o : list){
            records.add((Record) o);
        }
        return records;
    }

    @Override
    public List<Record> getAll() throws Exception {
        List list = baseDao.getAllList(Record.class);
        List<Record> records = new ArrayList<>();
        for(Object o : list){
            records.add((Record) o);
        }
        return records;
    }

    @Override
    public Set<String> getOidByAid(int activityid) throws Exception {
        List list = baseDao.query("from cn.edu.nju.software.models.Record as r where r.activityid = " + activityid);
        Set<String> orderids = new HashSet<>();
        for(Object o : list){
            orderids.add(String.valueOf(((Record) o).getOrderid()));
        }
        return orderids;
    }
}
