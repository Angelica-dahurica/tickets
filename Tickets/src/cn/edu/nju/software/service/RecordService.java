package cn.edu.nju.software.service;

import cn.edu.nju.software.models.Record;

import java.util.List;

public interface RecordService {

    public void save(Record record) throws Exception;

    public List<Record> getAllByAid(String activityid) throws Exception;

    public List<Record> getAllAdd() throws Exception;

    public List<Record> getAllMinus() throws Exception;
}
