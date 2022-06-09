package com.bookmanager.service;

import com.bookmanager.dao.ManagerDao;
import com.bookmanager.pojo.Manager;

import javax.servlet.http.HttpSession;

/**
 * @Classname LoginService
 * @Description TODO
 * @Date 2022/6/7 22:25
 * @Created by 晨曦
 */
public class LoginService {
    public boolean regist(Manager manager){
        ManagerDao managerDao = new ManagerDao();
        int count = managerDao.addManager(manager);
        if (count != 0) {
            return true;
        }
        return false;
    }

    public boolean login(String username, String password, HttpSession session) {
        ManagerDao dao = new ManagerDao();
        Manager manager = dao.queryManagerByUsername(username);
        if (manager==null) {
            return false;
        }
        if (manager.getPwd().equals(password)) {
            session.setAttribute("Manager",manager);
            return true;
        }
        return false;
    }
}
