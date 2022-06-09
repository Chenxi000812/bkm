package com.bookmanager.dao;

import com.bookmanager.pojo.Book;
import com.bookmanager.pojo.QueryObject;
import com.bookmanager.util.MysqlUtil;
import com.mysql.cj.util.StringUtils;

import java.sql.*;
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
            PreparedStatement countStatement = connection.prepareStatement(sql.toString().replace("*","count(*)"));
            MysqlUtil.LIMIT(sql,page,15);
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());


            int index = 1;
            if (!StringUtils.isNullOrEmpty(word)){
                preparedStatement.setString(index,"%"+word+"%");
                countStatement.setString(index++,"%"+word+"%");
            }
            if (!StringUtils.isNullOrEmpty(type)){
                preparedStatement.setString(index,type);
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
    public int delBook(Long bookId){
        Connection connection = MysqlUtil.getConnection();
        int i = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM book where bookid = ?");
            statement.setLong(1,bookId);
            i = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public int addBook(Book book) {
        Connection connection = MysqlUtil.getConnection();
        int i = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO book values (null,?,?,?,?,?)");
            statement.setString(1,book.getBookname());
            statement.setString(2,book.getBooktype());
            statement.setInt(3,book.getPrice());
            statement.setInt(4,book.getNum());
            statement.setDate(5,new Date(System.currentTimeMillis()));
            i = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    public int updateBook(Book book) {
        Connection connection = MysqlUtil.getConnection();
        int i = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("update book set bookname=?,booktype=?,price=?,num=? where bookid=?");
            statement.setString(1,book.getBookname());
            statement.setString(2,book.getBooktype());
            statement.setInt(3,book.getPrice());
            statement.setInt(4,book.getNum());
            statement.setLong(5,book.getBookid());
            i = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
}
