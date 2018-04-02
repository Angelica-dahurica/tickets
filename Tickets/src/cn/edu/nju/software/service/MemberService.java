package cn.edu.nju.software.service;

import cn.edu.nju.software.models.*;

import java.util.List;

public interface MemberService {

    public Member checkMemberLogin(String email, String password) throws Exception;

    public void register(Member member) throws Exception;

    public void updateMember(Member member) throws Exception;

    public void cancelQualification(String email) throws Exception;

    public Member findMember(String email) throws Exception;

    public int submitOrder(Member member) throws Exception;

    public void prebookTickets(String email, int activityid, int orderid, String[] price, String[] quantity, String[] type) throws Exception;

    public List<Order> getOrderByEmail(String email) throws Exception;

    public List<Record> getMemberRecords(List<Order> orders) throws Exception;

    public List<Presale> getPresaleByOid(int orderid) throws Exception;

    public void updatePaidBook(String orderid) throws Exception;

    public void updatePaidBook(String orderid, List tickets) throws Exception;

    public void updateQuitBook(String orderid) throws Exception;

    public Manager getManager() throws Exception;

    public List<Member> getAllMembers() throws Exception;

    public void updateManager(Manager manager) throws Exception;

    public void saveCoupon(String email) throws Exception;
}