package com.bookmanager.dao;

import com.bookmanager.pojo.Book;
import com.bookmanager.util.MysqlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname BookDao
 * @Description TODO
 * @Date 2022/6/7 22:26
 * @Created by 晨曦
 */
public class BookDao {

    public List<Book> queryBook() {
        Connection connection = MysqlUtil.getConnection();
        List<Book> bookList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM book");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Book book = new Book();
                book.setBookid(resultSet.getLong(1));
                book.setBookname(resultSet.getString(2));
                book.setBooktype(resultSet.getString(3));
                book.setPrice(resultSet.getInt(4));
                book.setNum(resultSet.getInt(5));
                book.setDate(resultSet.getDate(6));
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }
}
