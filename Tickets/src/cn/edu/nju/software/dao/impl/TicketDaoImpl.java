package cn.edu.nju.software.dao.impl;

import cn.edu.nju.software.dao.BaseDao;
import cn.edu.nju.software.dao.SeatDao;
import cn.edu.nju.software.dao.TicketDao;
import cn.edu.nju.software.models.Activity;
import cn.edu.nju.software.models.Seat;
import cn.edu.nju.software.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketDaoImpl implements TicketDao {

    @Autowired
    private BaseDao baseDao;
    private SeatDao seatDao;

    public BaseDao getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    public SeatDao getSeatDao() {
        return seatDao;
    }

    public void setSeatDao(SeatDao seatDao) {
        this.seatDao = seatDao;
    }

    @Override
    public List<String> getTicketids(String activityid) throws Exception {
        List list = baseDao.query("from cn.edu.nju.software.models.Ticket as t where t.activityid = " + activityid);
        List<String> tickets = new ArrayList<>();
        for(Object o : list) {
            tickets.add(String.valueOf(((Ticket) o).getTicketid()));
        }
        return tickets;
    }

    @Override
    public List<Ticket> getTickets(String activityid) throws Exception {
        List list = baseDao.query("from cn.edu.nju.software.models.Ticket as t where t.activityid = " + activityid);
        List<Ticket> tickets = new ArrayList<>();
        for(Object o : list) {
            tickets.add((Ticket) o);
        }
        return tickets;
    }

    @Override
    public void delete(Ticket ticket) throws Exception {
        baseDao.delete(ticket);
    }

    @Override
    public void saveTickets(ArrayList<Ticket> tickets) throws Exception {
        for(Ticket ticket: tickets) {
            baseDao.save(ticket);
        }
    }

    @Override
    public void saveTicket(Ticket ticket) throws Exception {
        baseDao.save(ticket);
    }

    @Override
    public void updateTicket(Ticket ticket) throws Exception {
        baseDao.update(ticket);
    }

    @Override
    public Ticket findTicket(String ticketid) throws Exception {
        return (Ticket) baseDao.load(Ticket.class, Integer.parseInt(ticketid));
    }

    @Override
    public Ticket findTicketByRCAP(int row, int col, int activityid, double price) throws Exception {
        List list = baseDao.query("from cn.edu.nju.software.models.Ticket as t where t.activityid = " + activityid
        + " and t.row = " + row + " and t.col = " + col + " and t.price = " + price);
        return (Ticket) list.get(0);
    }

    @Override
    public List<Ticket> findTicketByAM(String aid, String email) throws Exception {
        List list = baseDao.query("from cn.edu.nju.software.models.Ticket as t where t.activityid = " + aid
                + " and t.lockperson = '" + email + "'");
        List<Ticket> tickets = new ArrayList<>();
        for(Object o : list) {
            tickets.add((Ticket) o);
        }
        return tickets;
    }

    @Override
    public List<Ticket> getTicketsUnpaid() throws Exception {
        List list = baseDao.query("from cn.edu.nju.software.models.Ticket as t where t.status = " + 1);
        List<Ticket> tickets = new ArrayList<>();
        for(Object o : list) {
            tickets.add((Ticket) o);
        }
        return tickets;
    }

    @Override
    public List<Ticket> allocateTickets(List<Seat> seats, Activity activity) throws Exception {
        int seatNum;
        int seatType;
        ArrayList<Ticket> tickets = new ArrayList<>();
        for(Seat seat : seats){
            seatNum = seat.getNum();
            seatType = seat.getType();
            int row = 1;
            while(seatNum > 0){
                for(int col = 1; col <= 20 && seatNum>0; col++){
                    Ticket ticket = new Ticket();
                    ticket.setActivityid(activity.getActivityid());
                    ticket.setStatus(2);
                    ticket.setSeattype(seatType);
                    ticket.setPrice(seatDao.findSeat(String.valueOf(seat.getSeatid())).getPrice());
                    ticket.setRow(row);
                    ticket.setCol(col);
                    tickets.add(ticket);
                    seatNum--;
                }
                row++;
            }
        }
        saveTickets(tickets);
        return tickets;
    }

    @Override
    public List<Ticket> getTicketsOnselling(String price, int activityid) throws Exception {
        List list = baseDao.query("from cn.edu.nju.software.models.Ticket as t where t.status = " + 2 +
        " and t.price = " + price + " and t.activityid = " + activityid);
        List<Ticket> tickets = new ArrayList<>();
        for(Object o : list) {
            tickets.add((Ticket) o);
        }
        return tickets;
    }

}
