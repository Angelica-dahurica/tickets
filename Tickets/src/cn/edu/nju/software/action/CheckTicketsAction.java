package cn.edu.nju.software.action;

import org.springframework.stereotype.Controller;

@Controller
public class CheckTicketsAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    @Override
    public String execute() throws Exception {
        return "checkTickets";
    }

}
