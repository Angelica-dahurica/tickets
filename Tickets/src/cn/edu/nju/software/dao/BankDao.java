package cn.edu.nju.software.dao;

import cn.edu.nju.software.models.Bank;

import java.util.List;

public interface BankDao {

    public void saveBank(Bank bank) throws Exception;

    public List<Bank> findBankListByEmail(String email) throws Exception;

    public void updateBank(Bank bank) throws Exception;

    public void deleteBank(Bank bank) throws Exception;

    public Bank findBank(String accountid) throws Exception;

}
