package cn.edu.nju.software.dao;

import cn.edu.nju.software.models.Activity;
import cn.edu.nju.software.models.Seat;
import cn.edu.nju.software.models.Ticket;

import java.util.ArrayList;
import java.util.List;

public interface TicketDao {

    public List<Ticket> getTickets(String activityid) throws Exception;

    public List<String> getTicketids(String activityid) throws Exception;

    public void delete(Ticket ticket) throws Exception;

    public void saveTickets(ArrayList<Ticket> tickets) throws Exception;

    public void updateTicket(Ticket ticket) throws Exception;

    public void saveTicket(Ticket ticket) throws Exception;

    public Ticket findTicket(String ticketid) throws Exception;

    public Ticket findTicketByRCAP(int row, int col, int activityid, double price) throws Exception;

    public List<Ticket> findTicketByAM(String aid, String email) throws Exception;

    public List<Ticket> getTicketsUnpaid() throws Exception;

    public List<Ticket> allocateTickets(List<Seat> seats, Activity activity) throws Exception;

    public List<Ticket> getTicketsOnselling(String price, int activityid) throws Exception;
}
