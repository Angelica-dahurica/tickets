package cn.edu.nju.software.dao.impl;

import cn.edu.nju.software.dao.BankDao;
import cn.edu.nju.software.dao.BaseDao;
import cn.edu.nju.software.models.Bank;
import cn.edu.nju.software.models.CardOwn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BankDaoImpl implements BankDao {

    @Autowired
    private BaseDao baseDao;

    public BaseDao getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public void saveBank(Bank bank) throws Exception {
        baseDao.save(bank);
    }

    @Override
    public List<Bank> findBankListByEmail(String email) throws Exception {
        List list = baseDao.query("from cn.edu.nju.software.models.CardOwn as b where b.email = '" + email + "'");
        List<Bank> banks = new ArrayList<>();
        for(Object o : list){
            banks.add((Bank) baseDao.load(Bank.class, ((CardOwn) o).getAccountid()));
        }
        return banks;
    }

    @Override
    public void updateBank(Bank bank) throws Exception {
        baseDao.update(bank);
    }

    @Override
    public void deleteBank(Bank bank) throws Exception {
        baseDao.delete(bank);
    }

    @Override
    public Bank findBank(String accountid) throws Exception {
        return (Bank) baseDao.load(Bank.class, Integer.parseInt(accountid));
    }

}
