package cn.edu.nju.software.action;

import cn.edu.nju.software.models.Member;
import cn.edu.nju.software.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sun.misc.BASE64Encoder;

@Controller
public class RegisterMemberAction extends BaseAction {

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
        String verification = request.getParameter("verification");
        String verify = (new BASE64Encoder()).encodeBuffer((email + "tickets").getBytes());

        if (verification.equals(verify.trim())) {
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            int age = Integer.parseInt(request.getParameter("age"));
            String address = request.getParameter("address");
            int sex = request.getParameter("sex").equals("男") ? 1 : 0;

            Member member = new Member();
            member.setEmail(email);
            member.setName(name);
            member.setPassword(password);
            member.setAge(age);
            member.setAddress(address);
            member.setSex(sex);

            member.setConsumption(0);
            member.setScore(0);
            member.setLevel(1);
            member.setActive(1);

            memberService.register(member);
            return "registerMemberSuccess";
        } else {
            return "registerError";
        }
    }
}
