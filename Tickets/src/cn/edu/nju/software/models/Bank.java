package cn.edu.nju.software.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "cn.edu.nju.software.models.Bank")
@Table(name = "bank")
public class Bank {

    @Id
    @Column(name = "accountid")
    private int accountid;

    @Column(name = "paypassword")
    private String paypassword;

    @Column(name = "balance")
    private double balance;

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public String getPaypassword() {
        return paypassword;
    }

    public void setPaypassword(String paypassword) {
        this.paypassword = paypassword;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
