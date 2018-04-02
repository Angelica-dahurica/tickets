package cn.edu.nju.software.service;

import cn.edu.nju.software.models.Record;
import cn.edu.nju.software.models.Venue;

import java.util.List;

public interface VenueService {

    public int register(Venue venue) throws Exception;

    public void updateVenue(Venue venue) throws Exception;

    public Venue findVenue(String venueid) throws Exception;

    public List<Venue> getToCheckVenues() throws Exception;

    public List<String> getOrderByVenueid(int venueid) throws Exception;

    public List<Record> getVenueRecords(List<String> aids) throws Exception;

    public List<Venue> getAllVenues() throws Exception;
}
