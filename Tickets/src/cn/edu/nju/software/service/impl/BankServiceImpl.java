package cn.edu.nju.software.service.impl;

import cn.edu.nju.software.dao.BankDao;
import cn.edu.nju.software.dao.CardOwnDao;
import cn.edu.nju.software.dao.CouponDao;
import cn.edu.nju.software.models.Bank;
import cn.edu.nju.software.models.CardOwn;
import cn.edu.nju.software.models.Coupon;
import cn.edu.nju.software.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankDao bankDao;
    private CardOwnDao cardOwnDao;
    private CouponDao couponDao;

    public CouponDao getCouponDao() {
        return couponDao;
    }

    public void setCouponDao(CouponDao couponDao) {
        this.couponDao = couponDao;
    }

    public BankDao getBankDao() {
        return bankDao;
    }

    public void setBankDao(BankDao bankDao) {
        this.bankDao = bankDao;
    }

    public CardOwnDao getCardOwnDao() {
        return cardOwnDao;
    }

    public void setCardOwnDao(CardOwnDao cardOwnDao) {
        this.cardOwnDao = cardOwnDao;
    }

    @Override
    public void bindBankCard(String email, Bank bank) throws Exception {
        bankDao.saveBank(bank);
        CardOwn cardOwn = new CardOwn();
        cardOwn.setAccountid(bank.getAccountid());
        cardOwn.setEmail(email);
        cardOwnDao.saveCardOwn(cardOwn);
    }

    @Override
    public List<Bank> findBankCardsByEmail(String email) throws Exception {
        return bankDao.findBankListByEmail(email);
    }

    @Override
    public void unbindBankCard(Bank bank) throws Exception {
        bankDao.deleteBank(bank);
    }

    @Override
    public double balanceAdd(String email, String accountid, String[] price, String[] quantity) throws Exception {
        Bank bank = bankDao.findBank(accountid);
        double total = 0;
        for(int i = 0; i < price.length; i++){
            total += Double.parseDouble(price[i]) * Double.parseDouble(quantity[i]);
        }

        //自动使用优惠券，一张订单只可使用一张优惠券
        Coupon coupon = couponDao.find(email);
        if(coupon != null) {
            coupon.setQuantity(coupon.getQuantity()-1);
            total -= 10;
            if(coupon.getQuantity() == 0) {
                couponDao.delete(coupon);
            } else {
                couponDao.update(coupon);
            }
        }
        if(bank.getBalance() - total > 0) {
            bank.setBalance(bank.getBalance() - total);
            bankDao.updateBank(bank);
            return total;
        } else {
            return 0;
        }
    }

    @Override
    public double balanceMinus(String accountid, double ratio, String[] price, String[] quantity) throws Exception {
        Bank bank = bankDao.findBank(accountid);
        double total = 0;
        for(int i = 0; i < price.length; i++){
            total += Double.parseDouble(price[i]) * Double.parseDouble(quantity[i]);
        }
        bank.setBalance(bank.getBalance() + total * ratio);
        bankDao.updateBank(bank);
        return total;
    }

    @Override
    public void save(CardOwn cardOwn) throws Exception {
        cardOwnDao.saveCardOwn(cardOwn);
    }

}
