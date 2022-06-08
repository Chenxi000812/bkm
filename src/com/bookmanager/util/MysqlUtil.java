package com.bookmanager.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Classname MysqlUtil
 * @Description TODO
 * @Date 2022/6/7 20:43
 * @Created by 晨曦
 */
public class MysqlUtil {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://rm-uf639dsvmh3631we45o.mysql.rds.aliyuncs.com:3306/bookmanager?characterEncoding=utf-8";
    public static final String USERNAME = "qq984077246";
    public static final String PASSWORD = "Guochenxi2021";
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static void hasWhere(StringBuilder sb,String x){
        if (sb.indexOf("where")==-1){
            sb.append(" where ");
        }else {
            sb.append(x);
        }
    }
    public static void AND(StringBuilder sb,String content){
        hasWhere(sb," and ");
        sb.append(content);
    }

    public static void LIMIT(StringBuilder sb,Long page, int count) {
        sb.append(" limit ");
        sb.append(String.valueOf((page-1)*count));
        sb.append(",");
        sb.append(String.valueOf(count));
    }
}
