package cn.edu.nju.software.action;

import cn.edu.nju.software.models.Activity;
import cn.edu.nju.software.models.Member;
import cn.edu.nju.software.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

@Controller
public class SubmitOrderAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    @Autowired
    private MemberService memberService;

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
        int orderid = memberService.submitOrder(member);
        String[] price = (String[]) session.getAttribute("price");
        String[] quantity = (String[]) session.getAttribute("quantity");
        Activity activity = (Activity) session.getAttribute("activity");
        String[] type = (String[]) session.getAttribute("type");
        memberService.prebookTickets(member.getEmail(), activity.getActivityid(), orderid, price, quantity, type);
        session.setAttribute("orderid", orderid);
        return "confirmOrder";
    }
}
