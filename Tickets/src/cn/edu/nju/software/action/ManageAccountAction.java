package cn.edu.nju.software.action;

import cn.edu.nju.software.models.Bank;
import cn.edu.nju.software.models.Member;
import cn.edu.nju.software.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ManageAccountAction extends BaseAction{

    private static final long serialVersionUID = 1L;

    @Autowired
    private BankService bankService;

    @Override
    public String execute() throws Exception {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        request.setAttribute("member", member);

        List<Bank> banks = bankService.findBankCardsByEmail(member.getEmail());
        request.setAttribute("banks", banks);
        return "manageMember";
    }

}
