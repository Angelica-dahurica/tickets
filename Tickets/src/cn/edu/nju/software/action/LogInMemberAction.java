package cn.edu.nju.software.action;

import cn.edu.nju.software.models.Member;
import cn.edu.nju.software.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

@Controller
public class LogInMemberAction extends BaseAction {

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
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Member member = memberService.checkMemberLogin(email, password);
        if (member != null) {
            if(member.getActive() == 0){
                return "memberHasCancelled";
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("member", member);
                session.setAttribute("type", "member");
                return "memberPage";
            }
        } else {
            return "logInError";
        }
    }

}
