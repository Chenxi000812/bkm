package com.bookmanager.dao;

import com.bookmanager.pojo.Book;
import com.bookmanager.util.MysqlUtil;
import com.mysql.cj.util.StringUtils;

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

    public List<Book> queryBook(int page, String word, String type) {
        Connection connection = MysqlUtil.getConnection();
        List<Book> bookList = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM book");
            if (!StringUtils.isNullOrEmpty(word)){
                MysqlUtil.AND(sql," bookname like ? ");
            }
            if (!StringUtils.isNullOrEmpty(type)){
                MysqlUtil.AND(sql," booktype=? ");
            }
            MysqlUtil.LIMIT(sql,page,15);
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            int index = 1;
            if (!StringUtils.isNullOrEmpty(word)){
                preparedStatement.setString(index++,"%"+word+"%");
            }
            if (!StringUtils.isNullOrEmpty(type)){
                preparedStatement.setString(index++,type);
            }

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
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }
}
