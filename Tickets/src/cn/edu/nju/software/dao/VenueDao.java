package cn.edu.nju.software.dao;

import cn.edu.nju.software.models.Venue;

import java.util.List;

public interface VenueDao {

    public void saveVenue(Venue venue) throws Exception;

    public void updateVenue(Venue venue) throws Exception;

    public Venue findVenue(String venueid) throws Exception;

    public List<Venue> getAllVenues() throws Exception;
}
