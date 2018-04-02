package cn.edu.nju.software.service.impl;

import cn.edu.nju.software.dao.TicketDao;
import cn.edu.nju.software.models.Member;
import cn.edu.nju.software.models.Ticket;
import cn.edu.nju.software.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDao ticketDao;

    public TicketDao getTicketDao() {
        return ticketDao;
    }

    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    public void invalidTicket(Ticket ticket) throws Exception {
        ticketDao.delete(ticket);
    }

    @Override
    public void lockTicket(Ticket ticket, Member member) throws Exception {
        Ticket t = ticketDao.findTicketByRCAP(ticket.getRow(), ticket.getCol(), ticket.getActivityid(), ticket.getPrice());

        if(t.getStatus() == 2) {  //表明没有人选
            t.setStatus(1);
            t.setLocktime(new Timestamp(System.currentTimeMillis()));
            t.setLockperson(member.getEmail());
            ticketDao.updateTicket(t);
        }
    }

    @Override
    public void lockTickets(List<Ticket> tickets) throws Exception {

    }

    @Override
    public void unlockTicket(Ticket ticket, Member member) throws Exception {
        Ticket t = ticketDao.findTicketByRCAP(ticket.getRow(), ticket.getCol(), ticket.getActivityid(), ticket.getPrice());

        if(t.getStatus() == 1) {  //表明有人选
            if(t.getLockperson().equals(member.getEmail())) {   //自己选的
                t.setStatus(2);
                t.setLocktime(null);
                t.setLockperson(null);
                ticketDao.updateTicket(t);
            }
        }
    }

    @Override
    public void unlockTickets(List<Ticket> tickets) throws Exception {

    }

    @Override
    public List<Ticket> getAllTicketsByAid(String aid) throws Exception {
        return ticketDao.getTickets(aid);
    }

    @Override
    public List<Ticket> getAllTicketsByAM(String aid, String email) throws Exception {
        return ticketDao.findTicketByAM(aid, email);
    }

    @Override
    public Ticket find(String tid) throws Exception {
        return ticketDao.findTicket(tid);
    }

    @Override
    public void update(Ticket t) throws Exception {
        ticketDao.updateTicket(t);
    }
}
