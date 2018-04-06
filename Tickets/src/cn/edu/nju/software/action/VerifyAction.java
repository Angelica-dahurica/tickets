package cn.edu.nju.software.action;

import cn.edu.nju.software.models.Member;
import cn.edu.nju.software.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sun.misc.BASE64Encoder;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Controller
public class VerifyAction extends BaseAction{

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
        Member member = null;
        try {
            member = memberService.findMember(email);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(member != null){
            if(member.getActive() == 0){
                return "memberHasCancelled";
            }
            return "memberHasExist";
        }

        final String smtpPort = "465";
        Properties props = new Properties();
        props.setProperty("mail.debug", "true");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.host", "smtp.163.com");
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);

        Session session = Session.getInstance(props);
        Message msg = new MimeMessage(session);
        msg.setSubject("Tickets订票系统");
        msg.setText("这是一封由Tickets订票系统发送的邮件！您的验证码为" + (new BASE64Encoder()).encodeBuffer((email + "tickets").getBytes()));
        //msg.setFrom(new InternetAddress("发送的邮箱", "Tickets订票系统", "UTF-8"));
        msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email, "", "UTF-8"));

        Transport transport = session.getTransport();
        //transport.connect("发送的邮箱", "该邮箱的密码");
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
        return "mailSent";
    }
}
