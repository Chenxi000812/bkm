package com.bookmanager.dao;

import com.bookmanager.pojo.Book;
import com.bookmanager.pojo.QueryObject;
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
    public QueryObject<Book> queryBook(Long page, String word, String type) {
        Connection connection = MysqlUtil.getConnection();
        QueryObject<Book> queryObject = new QueryObject<>();
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
            sql.replace(sql.indexOf("*"),sql.indexOf("*")+1,"count(*)");
            PreparedStatement countStatement = connection.prepareStatement(sql.toString());
            int index = 1;
            if (!StringUtils.isNullOrEmpty(word)){
                preparedStatement.setString(index++,"%"+word+"%");
                countStatement.setString(index++,"%"+word+"%");
            }
            if (!StringUtils.isNullOrEmpty(type)){
                preparedStatement.setString(index++,type);
                countStatement.setString(index++,"%"+word+"%");
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
            queryObject.setObjects(bookList);
            ResultSet totalResult = countStatement.executeQuery();
            totalResult.next();
            queryObject.setTotal(totalResult.getLong(1)); //设置总长度
            queryObject.setPageIndex(page);
            preparedStatement.close();
            countStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return queryObject;
    }
}
