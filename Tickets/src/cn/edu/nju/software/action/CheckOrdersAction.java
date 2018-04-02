package cn.edu.nju.software.action;

import cn.edu.nju.software.models.Member;
import cn.edu.nju.software.models.Order;
import cn.edu.nju.software.models.OrderInfo;
import cn.edu.nju.software.models.Presale;
import cn.edu.nju.software.service.ActivityService;
import cn.edu.nju.software.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CheckOrdersAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    @Autowired
    private MemberService memberService;
    private ActivityService activityService;

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

    @Override
    public String execute() throws Exception {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        List<Order> orderList = memberService.getOrderByEmail(member.getEmail());

        List<OrderInfo> orderInfos = new ArrayList<>();
        for(Order order: orderList) {
            List<Presale> presales = memberService.getPresaleByOid(order.getOrderid());
            for(Presale presale: presales) {
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setOrderid(order.getOrderid());
                orderInfo.setStatus(order.getStatus());
                orderInfo.setBooktime(order.getBooktime());
                orderInfo.setActivityid(presale.getOrderid());
                orderInfo.setActivityname(activityService.findActivity(String.valueOf(presale.getActivityid())).getName());
                orderInfo.setType(Integer.parseInt(presale.getType()) == 0 ? "内场" : "看台");
                orderInfo.setQuantity(presale.getQuantity());
                orderInfo.setPrice(presale.getPrice());
                orderInfos.add(orderInfo);
            }
        }

        request.setAttribute("orderInfos", orderInfos);
        return "checkOrders";
    }

}
