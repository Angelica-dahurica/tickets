package cn.edu.nju.software.action;

import cn.edu.nju.software.models.*;
import cn.edu.nju.software.service.ActivityService;
import cn.edu.nju.software.service.BankService;
import cn.edu.nju.software.service.MemberService;
import cn.edu.nju.software.service.RecordService;
import cn.edu.nju.software.utils.Operate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class QuitAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    @Autowired
    private BankService bankService;
    private MemberService memberService;
    private ActivityService activityService;
    private RecordService recordService;

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

    public ActivityService getActivityService() {
        return activityService;
    }

    public void setActivityService(ActivityService activityService) {
        this.activityService = activityService;
    }

    public RecordService getRecordService() {
        return recordService;
    }

    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
    }

    @Override
    public String execute() throws Exception {
        String orderid = request.getParameter("orderid");
        memberService.updateQuitBook(orderid);

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        List<Bank> banks = bankService.findBankCardsByEmail(member.getEmail());
        List<Presale> presales = memberService.getPresaleByOid(Integer.parseInt(orderid));
        String[] price = new String[presales.size()];
        String[] quantity = new String[presales.size()];
        for(int i = 0; i < presales.size(); i++) {
            price[i] = presales.get(i).getPrice();
            quantity[i] = presales.get(i).getQuantity();
        }

        double ratio = activityService.getDiff(presales.get(0).getActivityid());
        double total = bankService.balanceMinus(String.valueOf(banks.get(0).getAccountid()), ratio, price, quantity);
        double money = total * ratio;

        //记录
        Activity activity = activityService.findActivity(String.valueOf(presales.get(0).getActivityid()));
        Record record = new Record();
        record.setPrice(money);
        record.setOrderid(Integer.parseInt(orderid));
        record.setOperate(Operate.Quit.ordinal());
        record.setActivityname(activity.getName());
        record.setActivityid(activity.getActivityid());
        recordService.save(record);

        Manager manager = memberService.getManager();
        manager.setBala(manager.getBala() - money);
        memberService.updateManager(manager);
        return null;
    }
}
