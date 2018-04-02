package cn.edu.nju.software.service.impl;

import cn.edu.nju.software.dao.*;
import cn.edu.nju.software.models.*;
import cn.edu.nju.software.service.AutoService;
import cn.edu.nju.software.utils.ActivityStatus;
import cn.edu.nju.software.utils.OrderStatus;
import cn.edu.nju.software.utils.TicketStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class AutoServiceImpl implements AutoService {

    @Autowired
    private OrderDao orderDao;
    private TicketDao ticketDao;
    private ActivityDao activityDao;
    private RecordDao recordDao;
    private BookDao bookDao;
    private MemberDao memberDao;
    private BankDao bankDao;
    private PresaleDao presaleDao;
    private SeatDao seatDao;
    private SeatHavingDao seatHavingDao;
    private TicketOwnDao ticketOwnDao;

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public TicketDao getTicketDao() {
        return ticketDao;
    }

    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public ActivityDao getActivityDao() {
        return activityDao;
    }

    public void setActivityDao(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    public RecordDao getRecordDao() {
        return recordDao;
    }

    public void setRecordDao(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    public BookDao getBookDao() {
        return bookDao;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public MemberDao getMemberDao() {
        return memberDao;
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public PresaleDao getPresaleDao() {
        return presaleDao;
    }

    public void setPresaleDao(PresaleDao presaleDao) {
        this.presaleDao = presaleDao;
    }

    public SeatDao getSeatDao() {
        return seatDao;
    }

    public void setSeatDao(SeatDao seatDao) {
        this.seatDao = seatDao;
    }

    public SeatHavingDao getSeatHavingDao() {
        return seatHavingDao;
    }

    public void setSeatHavingDao(SeatHavingDao seatHavingDao) {
        this.seatHavingDao = seatHavingDao;
    }

    public TicketOwnDao getTicketOwnDao() {
        return ticketOwnDao;
    }

    public void setTicketOwnDao(TicketOwnDao ticketOwnDao) {
        this.ticketOwnDao = ticketOwnDao;
    }

    public BankDao getBankDao() {
        return bankDao;

    }

    public void setBankDao(BankDao bankDao) {
        this.bankDao = bankDao;
    }

    @Override
    public void handleOrders() throws Exception {
        long t1 = System.currentTimeMillis();
        List<Order> orderList = orderDao.getOrdersUnpaid();
        for(Order order: orderList){
            long t2 = order.getBooktime().getTime();
            if(t1 - t2 > 3 * 60 * 1000){
                order.setStatus(OrderStatus.CANCELLED.ordinal());
                orderDao.update(order);
            }
        }
    }

    @Override
    public void handleLocks() throws Exception {
        long t1 = System.currentTimeMillis();
        List<Ticket> ticketsUnpaid = ticketDao.getTicketsUnpaid();
        for(Ticket ticket: ticketsUnpaid){
            long t2 = ticket.getLocktime().getTime();
            if(t1 - t2 > 3 * 60 * 1000){
                ticket.setStatus(TicketStatus.ONSELLING.ordinal());
                ticket.setLocktime(null);
                ticket.setLockperson(null);
                ticketDao.updateTicket(ticket);
            }
        }
    }

    @Override
    public void handleScores() throws Exception {
        List<Activity> activities = activityDao.getActivitiesSetteled();
        for(Activity activity : activities) {
            Set<String> orderids = recordDao.getOidByAid(activity.getActivityid());
            for(String orderid : orderids) {
                List<Record> records = recordDao.find(Integer.parseInt(orderid));
                double score = 0;
                for (Record record : records) {
                    score -= record.getPrice();
                }

                String email = bookDao.getEmailByOids(orderid);
                Member member = memberDao.findMember(email);


                Bank bank = bankDao.findBankListByEmail(email).get(0);
                bank.setBalance(bank.getBalance() + getLevelScore(score, member.getLevel()));
                bankDao.updateBank(bank);

                member.setConsumption(member.getConsumption() + score);
                member.setScore(member.getScore() + (int) score);
                member.setLevel(getLevel(member.getScore()));
                memberDao.updateMember(member);
            }
        }
    }

    private double getLevelScore(double score, int level) {
        switch (level) {
            case 1: return score * 0.01;
            case 2: return score * 0.03;
            case 3: return score * 0.05;
            case 4: return score * 0.1;
            default: return 0;
        }
    }

    private int getLevel(int score) {
        if(score < 0) {
            return -1;
        } else if(score >= 0 && score <= 300) {
            return 1;
        } else if(score > 300 && score <= 800) {
            return 2;
        } else if(score > 800 && score <= 1400) {
            return 3;
        } else {
            return 4;
        }
    }

    @Override
    public void handleTickets() throws Exception {
        long t1 = System.currentTimeMillis();
        List<Activity> activities = activityDao.getAllActivityList();
        for(Activity activity: activities){
            long t2 = activity.getTime().getTime();
            if((t1 - t2)/1000 > 2 * 7 * 24 * 60 * 60){

                //存票
                List<SeatHaving> seatHavings = seatHavingDao.findSeats(String.valueOf(activity.getActivityid()));
                List<Seat> seats = new ArrayList<>();
                for(SeatHaving seatHaving : seatHavings) {
                    seats.add(seatDao.findSeat(String.valueOf(seatHaving.getSeatid())));
                }
                ticketDao.allocateTickets(seats, activity);

                //处理订单，开始配票
                Set<String> orderids = recordDao.getOidByAid(activity.getActivityid());
                for(String orderid: orderids) {
                    Order order = orderDao.findOrder(orderid);
                    if(order.getStatus() == OrderStatus.PAID.ordinal()) {
                        List<Presale> presales = presaleDao.find(Integer.parseInt(orderid));
                        for (Presale presale : presales) {
                            for (int j = 0; j < Integer.parseInt(presale.getQuantity()); j++) {
                                int ticketid = getTicketidByPrice(presale.getPrice(), activity.getActivityid());
                                if (ticketid != -1 && ticketid != 0) {
                                    //配票
                                    TicketOwn ticketOwn = new TicketOwn();
                                    ticketOwn.setOrderid(Integer.parseInt(orderid));
                                    ticketOwn.setTicketid(ticketid);
                                    ticketOwnDao.save(ticketOwn);

                                    //标记票已卖出
                                    Ticket ticket = ticketDao.findTicket(String.valueOf(ticketid));
                                    ticket.setStatus(TicketStatus.SOLDOUT.ordinal());
                                    ticketDao.updateTicket(ticket);
                                } else if (ticketid == 0) {
                                    //退款
                                    Bank bank = bankDao.findBankListByEmail(bookDao.getEmailByOids(orderid)).get(0);
                                    bank.setBalance(bank.getBalance() + Double.parseDouble(presale.getPrice()));

                                    //标记订单为退款订单
                                    order.setStatus(OrderStatus.REFUND.ordinal());
                                    orderDao.update(order);
                                }
                            }
                        }
                    }
                }

                activity.setStatus(ActivityStatus.ONSELLING.ordinal());
                activityDao.update(activity);
            }
        }
    }

    private int getTicketidByPrice(String price, int activityid) {
        try {
            List<Ticket> tickets = ticketDao.getTicketsOnselling(price, activityid);
            if(tickets.size() != 0) {
                return tickets.get(0).getActivityid();
            } else {    //退款
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
