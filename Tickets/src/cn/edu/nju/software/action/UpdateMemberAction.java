package cn.edu.nju.software.action;

import cn.edu.nju.software.models.Member;
import cn.edu.nju.software.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

@Controller
public class UpdateMemberAction extends BaseAction {

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

        String name = request.getParameter("name");
        int sex = request.getParameter("sex").equals("男") ? 1 : 0;
        int age = Integer.parseInt(request.getParameter("age"));
        String address = request.getParameter("address");

        member.setName(name);
        member.setSex(sex);
        member.setAge(age);
        member.setAddress(address);

        memberService.updateMember(member);
        request.setAttribute("email", member.getEmail());
        return null;
    }

}
