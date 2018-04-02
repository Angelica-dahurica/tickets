package cn.edu.nju.software.service.impl;

import cn.edu.nju.software.dao.PlanDao;
import cn.edu.nju.software.dao.RecordDao;
import cn.edu.nju.software.dao.VenueDao;
import cn.edu.nju.software.models.Record;
import cn.edu.nju.software.models.Venue;
import cn.edu.nju.software.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {

    @Autowired
    private VenueDao venueDao;
    private PlanDao planDao;
    private RecordDao recordDao;

    public VenueDao getVenueDao() {
        return venueDao;
    }

    public void setVenueDao(VenueDao venueDao) {
        this.venueDao = venueDao;
    }

    public PlanDao getPlanDao() {
        return planDao;
    }

    public void setPlanDao(PlanDao planDao) {
        this.planDao = planDao;
    }

    public RecordDao getRecordDao() {
        return recordDao;
    }

    public void setRecordDao(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    @Override
    public int register(Venue venue) throws Exception {
        venue.setVenueid(getNewVid());
        venue.setStatus(0);
        venue.setBala(0);
        venueDao.saveVenue(venue);
        return venue.getVenueid();
    }

    @Override
    public void updateVenue(Venue venue) throws Exception {
        venueDao.updateVenue(venue);
    }

    @Override
    public Venue findVenue(String venueid) throws Exception {
        return venueDao.findVenue(venueid);
    }

    @Override
    public List<Venue> getToCheckVenues() throws Exception {
        List<Venue> venueList = venueDao.getAllVenues();
        List<Venue> venues = new ArrayList<>();
        for(Venue venue: venueList) {
            if (venue.getStatus() != 1) {
                venues.add(venue);
            }
        }
        return venues;
    }

    private int getNewVid() throws Exception{
        while(true){
            int randNum = (int)(Math.random()*9999999)+1;
            String venueid = String.format("%07d", randNum);
            if(venueDao.findVenue(venueid) == null){
                return Integer.parseInt(venueid);
            }
        }
    }

    @Override
    public List<String> getOrderByVenueid(int venueid) throws Exception {
        return planDao.getAllActivityids(String.valueOf(venueid));
    }

    @Override
    public List<Record> getVenueRecords(List<String> aids) throws Exception {
        List<Record> records = new ArrayList<>();
        for(String aid : aids) {
            List<Record> tmp = recordDao.findByAid(aid);
            if(tmp != null) {
                for (Record r : tmp) {
                    records.add(r);
                }
            }
        }
        return records;
    }

    @Override
    public List<Venue> getAllVenues() throws Exception {
        return venueDao.getAllVenues();
    }

}
