package cn.edu.nju.software.action;

import cn.edu.nju.software.models.Activity;
import cn.edu.nju.software.models.Manager;
import cn.edu.nju.software.models.Member;
import cn.edu.nju.software.models.Record;
import cn.edu.nju.software.service.BankService;
import cn.edu.nju.software.service.MemberService;
import cn.edu.nju.software.service.RecordService;
import cn.edu.nju.software.utils.Operate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ConfirmOrderAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    @Autowired
    private MemberService memberService;
    private BankService bankService;
    private RecordService recordService;

    public MemberService getMemberService() {
        return memberService;
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    public BankService getBankService() {
        return bankService;
    }

    public void setBankService(BankService bankService) {
        this.bankService = bankService;
    }

    public RecordService getRecordService() {
        return recordService;
    }

    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
    }

    @Override
    public String execute() throws Exception {
        HttpSession session = request.getSession();
        Activity activity = (Activity) session.getAttribute("activity");

        Member member = (Member) session.getAttribute("member");
        String accountid = request.getParameter("accountid");
        String[] price = (String[]) session.getAttribute("price");
        String[] quantity = (String[]) session.getAttribute("quantity");
        int orderid = (int) session.getAttribute("orderid");

        double res = bankService.balanceAdd(member.getEmail(), accountid, price, quantity);
        if(res == 0) {
            return "notEnoughMoney";
        }

        List tickets = (List) session.getAttribute("tickets");
        if(tickets == null) {
            memberService.updatePaidBook(String.valueOf(orderid));
        } else {
            memberService.updatePaidBook(String.valueOf(orderid), tickets);
        }

        //记录
        Record record = new Record();
        record.setPrice(-res);
        record.setOrderid(orderid);
        record.setOperate(Operate.PAID.ordinal());
        record.setActivityname(activity.getName());
        record.setActivityid(activity.getActivityid());
        recordService.save(record);

        Manager manager = memberService.getManager();
        manager.setBala(manager.getBala() + res);
        memberService.updateManager(manager);

        return null;
    }
}
