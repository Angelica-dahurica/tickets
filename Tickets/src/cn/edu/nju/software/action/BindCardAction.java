package cn.edu.nju.software.action;

import cn.edu.nju.software.models.CardOwn;
import cn.edu.nju.software.models.Member;
import cn.edu.nju.software.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

@Controller
public class BindCardAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    @Autowired
    private BankService bankService;

    public BankService getBankService() {
        return bankService;
    }

    public void setBankService(BankService bankService) {
        this.bankService = bankService;
    }

    @Override
    public String execute() throws Exception {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        String accountid = request.getParameter("accountid");
        CardOwn cardOwn = new CardOwn();
        cardOwn.setAccountid(Integer.parseInt(accountid));
        cardOwn.setEmail(member.getEmail());
        bankService.save(cardOwn);
        return null;
    }

}
