package cn.edu.nju.software.action;

import cn.edu.nju.software.models.Member;
import cn.edu.nju.software.models.Ticket;
import cn.edu.nju.software.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;


public class UnlockSeatAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    @Autowired
    private TicketService ticketService;
    public TicketService getTicketService() {
        return ticketService;
    }
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public String execute() throws Exception {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");

        try{
            int row = (int)Double.parseDouble(request.getParameter("row"));
            int col = Integer.parseInt(request.getParameter("col"));
            double price = Double.parseDouble(request.getParameter("price"));
            int activityid = Integer.parseInt(request.getParameter("activityid"));

            Ticket ticket = new Ticket();
            ticket.setRow(row);
            ticket.setCol(col);
            ticket.setPrice(price);
            ticket.setActivityid(activityid);
            ticketService.unlockTicket(ticket, member);
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }


}
