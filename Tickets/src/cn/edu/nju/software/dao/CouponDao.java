package cn.edu.nju.software.dao;

import cn.edu.nju.software.models.Coupon;

public interface CouponDao {

    public void update(Coupon coupon) throws Exception;

    public Coupon find(String email) throws Exception;

    public void delete(Coupon coupon) throws Exception;

    public void save(Coupon coupon) throws Exception;
}
