package com.bookmanager.dao;

import com.bookmanager.pojo.Manager;
import com.bookmanager.util.MysqlUtil;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Classname ManagerDao
 * @Description TODO
 * @Date 2022/6/7 22:25
 * @Created by 晨曦
 */
public class ManagerDao {
    public int addManager(Manager manager){
        Connection connection = MysqlUtil.getConnection();
        String sql="insert into manager values(?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = (PreparedStatement) connection.prepareStatement(sql);
            ps.setString(1, manager.getId());
            ps.setString(2, manager.getName());
            ps.setString(3, manager.getPwd());
            int count = ps.executeUpdate();
            ps.close();
            connection.close();
            return count;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }
    public int delManager(Manager manager){
        Connection connection = MysqlUtil.getConnection();
        String sql="delete from manager where id=?";
        PreparedStatement ps = null;
        try {
            ps = (PreparedStatement) connection.prepareStatement(sql);
            ps.setString(1, manager.getId());
            ps.close();
            int count = ps.executeUpdate();
            connection.close();
            return count;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    public Manager queryManagerByUsername(String username) {
        Connection connection = MysqlUtil.getConnection();
        String sql="select * from manager where id = ?";
        PreparedStatement ps = null;
        Manager manager = null;
        try {
            ps=connection.prepareStatement(sql);
            ps.setString(1,username);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            manager = new Manager();
            manager.setId(resultSet.getString(1));
            manager.setName(resultSet.getString(2));
            manager.setPwd(resultSet.getString(3));
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manager;
    }

}
