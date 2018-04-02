package cn.edu.nju.software.action;

import cn.edu.nju.software.models.Activity;
import cn.edu.nju.software.models.Member;
import cn.edu.nju.software.models.Ticket;
import cn.edu.nju.software.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SubmitOrder1Action extends BaseAction {

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
        Activity activity = (Activity) session.getAttribute("activity");
        int orderid = memberService.submitOrder(member);
        session.setAttribute("orderid", orderid);

        List tickets = (List) session.getAttribute("tickets");

        String[] _price = new String[tickets.size()];
        String[] _type = new String[tickets.size()];
        int k = 0;
        for (Object ticket : tickets) {
            Ticket t = (Ticket) ticket;
            if (!has(_price, String.valueOf(t.getPrice()))) {
                _price[k] = String.valueOf(t.getPrice());
                _type[k] = t.getSeattype() == 0 ? "内场" : "看台";
                k++;
            }
        }

        String[] price = new String[k];
        String[] type = new String[k];
        for(int i = 0; i < k; i++) {
            price[i] = _price[i];
            type[i] = _type[i];
        }

        int[] quan = new int[k];
        for (Object o: tickets) {
            Ticket t = (Ticket) o;
            for (int i = 0; i < k; i++) {
                if (Double.parseDouble(price[i]) == t.getPrice()) {
                    quan[i]++;
                }
            }
        }

        String[] quantity = new String[k];
        for(int j = 0; j < quan.length; j++) {
            quantity[j] = String.valueOf(quan[j]);
        }

        memberService.prebookTickets(member.getEmail(), activity.getActivityid(), orderid,
        price, quantity, type);

        session.setAttribute("price", price);
        session.setAttribute("quantity", quantity);
        return "confirmOrder";
    }

    private boolean has(String[] price, String p) {
        for(String ss : price) {
           if(ss != null && ss.equals(p)) {
               return true;
           }
        }
        return false;
    }

}
