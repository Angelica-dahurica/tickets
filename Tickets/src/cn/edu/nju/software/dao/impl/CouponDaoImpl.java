package cn.edu.nju.software.dao.impl;

import cn.edu.nju.software.dao.BaseDao;
import cn.edu.nju.software.dao.CouponDao;
import cn.edu.nju.software.models.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CouponDaoImpl implements CouponDao {

    @Autowired
    private BaseDao baseDao;

    public BaseDao getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public void update(Coupon coupon) throws Exception {
        baseDao.update(coupon);
    }

    @Override
    public Coupon find(String email) throws Exception {
        List list = baseDao.query("from cn.edu.nju.software.models.Coupon as b where b.email = '" + email + "'");
        if(list.size() != 0) {
            return (Coupon) list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Coupon coupon) throws Exception {
        baseDao.delete(coupon);
    }

    @Override
    public void save(Coupon coupon) throws Exception {
        baseDao.save(coupon);
    }
}
