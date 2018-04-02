package cn.edu.nju.software.service;

import cn.edu.nju.software.models.Bank;
import cn.edu.nju.software.models.CardOwn;

import java.util.List;

public interface BankService {

    public void bindBankCard(String email, Bank bank) throws Exception;

    public List<Bank> findBankCardsByEmail(String email) throws Exception;

    public void unbindBankCard(Bank bank) throws Exception;

    public double balanceAdd(String email, String accountid, String[] price, String[] quantity) throws Exception;

    public double balanceMinus(String accountid, double ratio, String[] price, String[] quantity) throws Exception;

    public void save(CardOwn cardOwn) throws Exception;

}
