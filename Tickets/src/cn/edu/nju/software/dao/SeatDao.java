package cn.edu.nju.software.dao;

import cn.edu.nju.software.models.Seat;

public interface SeatDao {

    public void saveSeat(Seat seat) throws Exception;

    public Seat findSeat(String seatid) throws Exception;

    public void update(Seat seat) throws Exception;

}
