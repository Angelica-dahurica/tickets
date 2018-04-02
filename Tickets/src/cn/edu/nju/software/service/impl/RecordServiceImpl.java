package cn.edu.nju.software.service.impl;

import cn.edu.nju.software.dao.RecordDao;
import cn.edu.nju.software.models.Record;
import cn.edu.nju.software.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService{

    @Autowired
    private RecordDao recordDao;

    public RecordDao getRecordDao() {
        return recordDao;
    }

    public void setRecordDao(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    @Override
    public void save(Record record) throws Exception {
        recordDao.save(record);
    }

    @Override
    public List<Record> getAllByAid(String activityid) throws Exception {
        return recordDao.findByAid(activityid);
    }

    @Override
    public List<Record> getAllAdd() throws Exception {
        List<Record> records = new ArrayList<>();
        List<Record> tmp = getAllRecords();
        for(Record record : tmp) {
            if(record.getPrice() > 0) {
                records.add(record);
            }
        }
        return records;
    }

    @Override
    public List<Record> getAllMinus() throws Exception {
        List<Record> records = new ArrayList<>();
        List<Record> tmp = getAllRecords();
        for(Record record : tmp) {
            if(record.getPrice() < 0) {
                record.setPrice(-record.getPrice());
                records.add(record);
            }
        }
        return records;
    }

    private List<Record> getAllRecords() throws Exception {
        return recordDao.getAll();
    }

}
