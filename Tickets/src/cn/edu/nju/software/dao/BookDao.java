package cn.edu.nju.software.dao;

import cn.edu.nju.software.models.Book;

import java.util.List;

public interface BookDao {

    public void saveBook(Book book)throws Exception;

    public Book findBook(String name) throws Exception;

    public List<Book> getAllBookList(String email)throws Exception;

    public String getEmailByOids(String orderid) throws Exception;
}
