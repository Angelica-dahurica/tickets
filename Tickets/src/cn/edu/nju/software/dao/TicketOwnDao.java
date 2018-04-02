package cn.edu.nju.software.dao;

import cn.edu.nju.software.models.TicketOwn;

import java.util.List;

public interface TicketOwnDao {

    public List<String> getOrderids(String ticketid) throws Exception;

    public void save(TicketOwn ticketOwn) throws Exception;

    public void delete(TicketOwn ticketOwn) throws Exception;

    public List<TicketOwn> findByOid(String oid) throws Exception;

}
