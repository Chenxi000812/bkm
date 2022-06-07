package com.bookmanager.pojo;

import java.sql.Date;

/**
 * @Classname Book
 * @Description TODO
 * @Date 2022/6/7 21:11
 * @Created by 晨曦
 */
public class Book {
    private Long bookid;
    private String bookname;
    private String booktype;
    private Integer price;
    private Integer num;
    private Date date;
    public Long getBookid() {
        return bookid;
    }
    public void setBookid(Long bookid) {
        this.bookid = bookid;
    }
    public String getBookname() {
        return bookname;
    }
    public void setBookname(String bookname) {
        this.bookname = bookname;
    }
    public String getBooktype() {
        return booktype;
    }
    public void setBooktype(String booktype) {
        this.booktype = booktype;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
