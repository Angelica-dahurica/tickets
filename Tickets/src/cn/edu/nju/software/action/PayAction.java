package cn.edu.nju.software.action;

import cn.edu.nju.software.models.Bank;
import cn.edu.nju.software.models.Member;
import cn.edu.nju.software.models.Presale;
import cn.edu.nju.software.service.BankService;
import cn.edu.nju.software.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PayAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    @Autowired
    private BankService bankService;
    private MemberService memberService;

    public BankService getBankService() {
        return bankService;
    }

    public void setBankService(BankService bankService) {
        this.bankService = bankService;
    }

    public MemberService getMemberService() {
        return memberService;
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public String execute() throws Exception {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        List<Bank> banks = bankService.findBankCardsByEmail(member.getEmail());
        if(banks.size() == 0) {
            return "bindCard";
        } else {
            String orderid = request.getParameter("orderid");

            List<Presale> presales = memberService.getPresaleByOid(Integer.parseInt(orderid));
            String[] price = new String[presales.size()];
            String[] quantity = new String[presales.size()];
            for(int i = 0; i < presales.size(); i++) {
                price[i] = presales.get(i).getPrice();
                quantity[i] = presales.get(i).getQuantity();
            }

            session.setAttribute("banks", banks);
            session.setAttribute("price", price);
            session.setAttribute("quantity", quantity);
            session.setAttribute("orderid", Integer.parseInt(orderid));

            return "confirmOrder";
        }
    }
}
