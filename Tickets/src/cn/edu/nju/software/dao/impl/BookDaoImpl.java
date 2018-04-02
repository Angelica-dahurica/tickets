package cn.edu.nju.software.dao.impl;

import cn.edu.nju.software.dao.BaseDao;
import cn.edu.nju.software.dao.BookDao;
import cn.edu.nju.software.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private BaseDao baseDao;

    public BaseDao getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public void saveBook(Book book) throws Exception {
        baseDao.save(book);
    }

    @Override
    public Book findBook(String name) throws Exception {
        return (Book) baseDao.load(Book.class, name);
    }

    @Override
    public List<Book> getAllBookList(String email) throws Exception {
        List list = baseDao.query("from cn.edu.nju.software.models.Book as b where b.email = '" + email + "'");
        List<Book> books = new ArrayList<>();
        for (Object o : list) {
            books.add((Book) o);
        }
        return books;
    }

    @Override
    public String getEmailByOids(String orderid) throws Exception {
        List list = baseDao.query("from cn.edu.nju.software.models.Book as b where b.orderid = " + orderid + "");
        return ((Book) list.get(0)).getEmail();
    }

}
