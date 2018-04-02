package cn.edu.nju.software.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "cn.edu.nju.software.models.Manager")
@Table(name = "manager")
public class Manager {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "bala")
    private double bala;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBala() {
        return bala;
    }

    public void setBala(double bala) {
        this.bala = bala;
    }
}
