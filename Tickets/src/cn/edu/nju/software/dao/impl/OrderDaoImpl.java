package cn.edu.nju.software.dao.impl;

import cn.edu.nju.software.dao.BaseDao;
import cn.edu.nju.software.dao.OrderDao;
import cn.edu.nju.software.models.Order;
import cn.edu.nju.software.utils.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao{

    @Autowired
    private BaseDao baseDao;

    public BaseDao getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public void saveOrder(Order order) throws Exception {
        baseDao.save(order);
    }

    @Override
    public Order findOrder(String oid) throws Exception {
        return (Order) baseDao.load(Order.class, Integer.parseInt(oid));
    }

    @Override
    public List<Order> getOrdersUnpaid() throws Exception {
        List list = baseDao.getAllList(Order.class);
        List<Order> orders = new ArrayList<>();
        for(Object o: list) {
            Order order = (Order) o;
            if(order.getStatus() == OrderStatus.PAYING.ordinal())
                orders.add(order);
        }
        return orders;
    }

    @Override
    public void update(Order order) throws Exception {
        baseDao.update(order);
    }

}
