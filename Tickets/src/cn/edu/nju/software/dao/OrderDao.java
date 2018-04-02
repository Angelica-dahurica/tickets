package cn.edu.nju.software.dao;

import cn.edu.nju.software.models.Order;

import java.util.List;

public interface OrderDao {

    public void saveOrder(Order order)throws Exception;

    public Order findOrder(String oid) throws Exception;

    public List<Order> getOrdersUnpaid() throws Exception;

    public void update(Order order) throws Exception;

}
