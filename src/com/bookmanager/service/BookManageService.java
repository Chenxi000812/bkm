package com.bookmanager.service;

import com.bookmanager.dao.BookDao;
import com.bookmanager.pojo.Book;

import java.util.List;

/**
 * @Classname BookManageService
 * @Description TODO
 * @Date 2022/6/7 22:26
 * @Created by 晨曦
 */
public class BookManageService {
    public List<Book> getBooks() {
        BookDao bookDao = new BookDao();
        return bookDao.queryBook();
    }
}
