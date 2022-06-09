package com.bookmanager.service;

import com.bookmanager.dao.BookDao;
import com.bookmanager.pojo.Book;
import com.bookmanager.pojo.QueryObject;

import java.util.List;

/**
 * @Classname BookManageService
 * @Description TODO
 * @Date 2022/6/7 22:26
 * @Created by 晨曦
 */
public class BookManageService {
    public QueryObject<Book> getBooks(Long page, String word, String type) {
        BookDao bookDao = new BookDao();
        return bookDao.queryBook(page,word,type);
    }

    public boolean removeBook(Long bookId) {
        BookDao bookDao = new BookDao();
        return bookDao.delBook(bookId)==1;
    }

    public boolean addBook(Book book) {
        BookDao bookDao = new BookDao();
        return bookDao.addBook(book)==1;
    }

    public boolean editBook(Book book) {
        BookDao bookDao = new BookDao();
        return bookDao.updateBook(book)==1;
    }
}
