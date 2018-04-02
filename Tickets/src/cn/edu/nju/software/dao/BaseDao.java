package cn.edu.nju.software.dao;

import org.hibernate.Session;

import java.util.List;

public interface BaseDao {

    /**
     * 获取当前的session
     * @return session
     */
    public Session getSession();

    /**
     * 打开一个新的session
     * @return session
     * @throws Exception ex
     */
    public Session getNewSession() throws Exception;

    public void flush() throws Exception;

    public void clear() throws Exception;

    /**
     * 根据id查询信息
     * @param c class
     * @param id id
     * @return object
     * @throws Exception ex
     */
    public Object load(Class<?> c, String id) throws Exception;

    public Object load(Class<?> c,int id) throws Exception;

    /**
     * 获取所有信息
     * @param c class
     * @return list
     * @throws Exception ex
     */
    public List getAllList(Class c) throws Exception;

    /**
     * 保存
     * @param bean model
     * @throws Exception ex
     */
    public void save(Object bean) throws Exception;

    /**
     * 更新
     * @param bean model
     * @throws Exception ex
     */
    public void update(Object bean) throws Exception;

    /**
     * 删除
     * @param bean model
     * @throws Exception ex
     */
    public void delete(Object bean) throws Exception;

    /**
     * 根据ID删除
     * @param c class
     * @param id id
     * @throws Exception ex
     */
    public void delete(Class<?> c, int id) throws Exception;

    public List query(String hql) throws Exception;

}
