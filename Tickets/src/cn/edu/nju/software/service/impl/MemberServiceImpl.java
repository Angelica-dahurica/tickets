package cn.edu.nju.software.service.impl;

import cn.edu.nju.software.dao.*;
import cn.edu.nju.software.models.*;
import cn.edu.nju.software.service.MemberService;
import cn.edu.nju.software.utils.OrderStatus;
import cn.edu.nju.software.utils.TicketStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberDao memberDao;
    private OrderDao orderDao;
    private TicketOwnDao ticketOwnDao;
    private TicketDao ticketDao;
    private SeatDao seatDao;
    private PresaleDao presaleDao;
    private BookDao bookDao;
    private RecordDao recordDao;
    private ManagerDao managerDao;
    private CouponDao couponDao;

    public CouponDao getCouponDao() {
        return couponDao;
    }

    public void setCouponDao(CouponDao couponDao) {
        this.couponDao = couponDao;
    }

    public MemberDao getMemberDao() {
        return memberDao;
    }

    public ManagerDao getManagerDao() {
        return managerDao;
    }

    public void setManagerDao(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public TicketOwnDao getTicketOwnDao() {
        return ticketOwnDao;
    }

    public void setTicketOwnDao(TicketOwnDao ticketOwnDao) {
        this.ticketOwnDao = ticketOwnDao;
    }

    public TicketDao getTicketDao() {
        return ticketDao;
    }

    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public SeatDao getSeatDao() {
        return seatDao;
    }

    public void setSeatDao(SeatDao seatDao) {
        this.seatDao = seatDao;
    }

    public PresaleDao getPresaleDao() {
        return presaleDao;
    }

    public void setPresaleDao(PresaleDao presaleDao) {
        this.presaleDao = presaleDao;
    }

    public BookDao getBookDao() {
        return bookDao;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public RecordDao getRecordDao() {
        return recordDao;
    }

    public void setRecordDao(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    @Override
    public Member checkMemberLogin(String email, String password) throws Exception {
        Member member = memberDao.findMember(email);
        if(member == null){
            return null;
        }else if(member.getPassword().equals(password)){
            return member;
        }
        return null;
    }

    @Override
    public void register(Member member) throws Exception {
        memberDao.saveMember(member);
    }

    @Override
    public void updateMember(Member member) throws Exception {
        memberDao.updateMember(member);
    }

    @Override
    public void cancelQualification(String email) throws Exception {
        Member member = memberDao.findMember(email);
        member.setActive(0);
        memberDao.updateMember(member);
    }

    @Override
    public Member findMember(String email) throws Exception {
        return memberDao.findMember(email);
    }

    @Override
    public int submitOrder(Member member) throws Exception {
        Order order = new Order();
        order.setOrderid(getNewOid());
        order.setBooktime(new Timestamp(Calendar.getInstance().getTime().getTime()));
        order.setStatus(OrderStatus.PAYING.ordinal());
        orderDao.saveOrder(order);

        Book book = new Book();
        book.setEmail(member.getEmail());
        book.setOrderid(order.getOrderid());
        bookDao.saveBook(book);

        return order.getOrderid();
    }

    @Override
    public void prebookTickets(String email, int activityid, int orderid,
                               String[] price, String[] quantity, String[] type) throws Exception {
        Presale presale = new Presale();
        presale.setEmail(email);
        presale.setActivityid(activityid);
        presale.setOrderid(orderid);
        for(int i = 0; i < price.length; i++) {
            presale.setPrice(price[i]);
            presale.setQuantity(quantity[i]);
            presale.setType(String.valueOf(type[i].equals("内场") ? 0 : 1));
            presaleDao.save(presale);
        }
    }

    @Override
    public void updatePaidBook(String orderid) throws Exception {
        Order order = orderDao.findOrder(String.valueOf(orderid));
        order.setStatus(OrderStatus.PAID.ordinal());
        orderDao.update(order);
    }

    @Override
    public void updatePaidBook(String orderid, List tickets) throws Exception {
        Order order = orderDao.findOrder(String.valueOf(orderid));
        order.setStatus(OrderStatus.PAID.ordinal());
        orderDao.update(order);

        for(Object o : tickets) {
            Ticket t = (Ticket) o;
            t.setLocktime(null);
            t.setLockperson(null);
            t.setStatus(TicketStatus.SOLDOUT.ordinal());
            ticketDao.updateTicket(t);

            TicketOwn ticketOwn = new TicketOwn();
            ticketOwn.setOrderid(order.getOrderid());
            ticketOwn.setTicketid(t.getTicketid());
            ticketOwnDao.save(ticketOwn);
        }
    }

    @Override
    public void updateQuitBook(String orderid) throws Exception {
        List<TicketOwn> ticketOwns = ticketOwnDao.findByOid(orderid);
        if(ticketOwns!= null && ticketOwns.size()!=0) {
            for(TicketOwn ticketOwn : ticketOwns) {
                ticketOwnDao.delete(ticketOwn);
                Ticket ticket = ticketDao.findTicket(String.valueOf(ticketOwn.getTicketid()));
                ticket.setStatus(TicketStatus.ONSELLING.ordinal());
                ticketDao.updateTicket(ticket);
            }
        }

        Order order = orderDao.findOrder(String.valueOf(orderid));
        order.setStatus(OrderStatus.QUIT.ordinal());
        orderDao.update(order);
    }

    @Override
    public Manager getManager() throws Exception {
        return managerDao.getManager();
    }

    @Override
    public List<Member> getAllMembers() throws Exception {
        return memberDao.getAllMembers();
    }

    @Override
    public void updateManager(Manager manager) throws Exception {
        managerDao.update(manager);
    }

    @Override
    public void saveCoupon(String email) throws Exception {
        Coupon cou = couponDao.find(email);
        if(cou == null) {
            Coupon coupon = new Coupon();
            coupon.setEmail(email);
            coupon.setPrice(10);
            coupon.setQuantity(1);
            couponDao.save(coupon);
        } else {
            cou.setQuantity(cou.getQuantity() + 1);
            couponDao.update(cou);
        }
    }

    @Override
    public List<Order> getOrderByEmail(String email) throws Exception {
        List<Book> books = bookDao.getAllBookList(email);
        List<Order> orders = new ArrayList<>();
        for(Book book: books) {
            orders.add(orderDao.findOrder(String.valueOf(book.getOrderid())));
        }
        return orders;
    }

    @Override
    public List<Record> getMemberRecords(List<Order> orders) throws Exception {
        List<Record> records = new ArrayList<>();

        for(Order o : orders) {
            List<Record> tmp = recordDao.find(o.getOrderid());
            if(tmp != null) {
                for (Record r : tmp) {
                    records.add(r);
                }
            }
        }
        return records;
    }

    @Override
    public List<Presale> getPresaleByOid(int orderid) throws Exception {
        return presaleDao.find(orderid);
    }

    private int getNewOid() throws Exception{
        while(true){
            int randNum = (int)(Math.random()*9999999)+1;
            String orderid = String.format("%09d", randNum);
            if(orderDao.findOrder(orderid) == null){
                return Integer.parseInt(orderid);
            }
        }
    }

    private int getNewTid() throws Exception{
        while(true){
            int randNum = (int)(Math.random()*9999999)+1;
            String ticketid = String.format("%09d", randNum);
            if(ticketDao.findTicket(ticketid) == null){
                return Integer.parseInt(ticketid);
            }
        }
    }

    private void balance(List<Seat> seats, String price) throws Exception {
        for(Seat seat: seats){
            if (seat.getPrice() == Double.parseDouble(price)) {
                seat.setNum(seat.getNum()-1);
                seatDao.update(seat);
            }
        }
    }

}
