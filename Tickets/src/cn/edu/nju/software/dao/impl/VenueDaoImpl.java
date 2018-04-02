package cn.edu.nju.software.dao.impl;

import cn.edu.nju.software.dao.BaseDao;
import cn.edu.nju.software.dao.VenueDao;
import cn.edu.nju.software.models.Venue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VenueDaoImpl implements VenueDao {

    @Autowired
    private BaseDao baseDao;

    public BaseDao getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public void saveVenue(Venue venue) throws Exception {
        baseDao.save(venue);
    }

    @Override
    public void updateVenue(Venue venue) throws Exception {
        baseDao.update(venue);
    }

    @Override
    public Venue findVenue(String venueid) throws Exception {
        return (Venue) baseDao.load(Venue.class, Integer.parseInt(venueid));
    }

    @Override
    public List<Venue> getAllVenues() throws Exception {
        List list = baseDao.getAllList(Venue.class);
        List<Venue> venues = new ArrayList<>();
        for (Object o : list) {
            venues.add((Venue) o);
        }
        return venues;
    }

}
