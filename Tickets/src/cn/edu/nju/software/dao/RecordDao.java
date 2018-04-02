package cn.edu.nju.software.dao;

import cn.edu.nju.software.models.Record;

import java.util.List;
import java.util.Set;

public interface RecordDao {

    public void save(Record record) throws Exception;

    public List<Record> find(int orderid) throws Exception;

    public List<Record> findByAid(String aid) throws Exception;

    public List<Record> getAll() throws Exception;

    public Set<String> getOidByAid(int activityid) throws Exception;
}
