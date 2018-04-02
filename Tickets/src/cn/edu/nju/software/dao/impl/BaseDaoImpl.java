package cn.edu.nju.software.dao.impl;

import cn.edu.nju.software.dao.BaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BaseDaoImpl implements BaseDao{

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Session getNewSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void flush() {
        getSession().flush();
    }

    @Override
    public void clear() {
        getSession().clear();
    }

    @Override
    public Object load(Class<?> c, String id) {
        Session session = getSession();
        return session.get(c, id);
    }

    @Override
    public Object load(Class<?> c, int id) throws Exception {
        Session session = getNewSession();
        return session.get(c, id);
    }

    @Override
    public List getAllList(Class c) {
        String hql = "from " + c.getName();
        Session session = getSession();
        return session.createQuery(hql).list();
    }

    @Override
    public void save(Object bean) {
        try {
            Session session = getNewSession();
            Transaction tx = session.beginTransaction();
            session.save(bean);
            tx.commit();
            session.clear();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Object bean) {
        Session session = getNewSession();
        Transaction tx = session.beginTransaction();
        session.update(bean);
        tx.commit();
        session.clear();
        session.close();
    }

    @Override
    public void delete(Object bean) {
        Session session = getNewSession();
        Transaction tx = session.beginTransaction();
        session.delete(bean);
        tx.commit();
        session.clear();
        session.close();
    }

    @Override
    public void delete(Class<?> c, int id) {
        Session session = getNewSession();
        Object obj = session.get(c, id);
        Transaction tx = session.beginTransaction();
        session.delete(obj);
        tx.commit();
        session.clear();
        session.close();
    }

    @Override
    public List query(String hql) {
        Session session = getNewSession();
        Transaction tx = session.beginTransaction();
        List list = session.createQuery(hql).list();
        tx.commit();
        session.clear();
        session.close();
        return list;
    }

}
