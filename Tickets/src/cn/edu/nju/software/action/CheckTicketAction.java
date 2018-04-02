package cn.edu.nju.software.action;

import cn.edu.nju.software.models.Ticket;
import cn.edu.nju.software.service.TicketService;
import cn.edu.nju.software.utils.TicketStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CheckTicketAction extends BaseAction {

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
        String tid = request.getParameter("tid");
        Ticket t = ticketService.find(tid);
        if(t.getStatus() != TicketStatus.CHECKED.ordinal()) {
            t.setStatus(TicketStatus.CHECKED.ordinal());
            ticketService.update(t);
            return "checkTicketSuccess";
        } else {
            return "checkTicketError";
        }

    }

}
