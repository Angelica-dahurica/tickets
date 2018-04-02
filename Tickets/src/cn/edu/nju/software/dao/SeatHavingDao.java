package cn.edu.nju.software.dao;

import cn.edu.nju.software.models.SeatHaving;

import java.util.List;

public interface SeatHavingDao {

    public void saveSeatHaving(SeatHaving seatHaving) throws Exception;

    public List<SeatHaving> findSeats(String aid) throws Exception;

}
