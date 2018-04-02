package cn.edu.nju.software.models;

import javax.persistence.*;

@Entity(name = "cn.edu.nju.software.models.CardOwn")
@Table(name = "own_card")
@IdClass(value = CardOwnPrimaryKey.class)
public class CardOwn {

    @Id
    @Column(name = "email")
    private String email;

    @Id
    @Column(name = "accountid")
    private int accountid;

    @JoinColumn(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JoinColumn(name = "accountid")
    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

}
