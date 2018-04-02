package cn.edu.nju.software.service;

import cn.edu.nju.software.models.Member;
import cn.edu.nju.software.models.Ticket;

import java.util.List;

public interface TicketService {

    public void invalidTicket(Ticket ticket) throws Exception;

    public void lockTicket(Ticket ticket, Member member) throws Exception;

    public void lockTickets(List<Ticket> tickets) throws Exception;

    public void unlockTicket(Ticket ticket, Member member) throws Exception;

    public void unlockTickets(List<Ticket> tickets) throws Exception;

    public List<Ticket> getAllTicketsByAid(String aid) throws Exception;

    public List<Ticket> getAllTicketsByAM(String aid, String email) throws Exception;

    public Ticket find(String tid) throws Exception;

    public void update(Ticket t) throws Exception;
}
