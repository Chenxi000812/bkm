package com.bookmanager.servlet; /**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2022/6/8 18:08
 * @Created by 晨曦
 */

import com.bookmanager.pojo.Manager;
import com.bookmanager.service.LoginService;
import com.bookmanager.util.AjaxResult;
import com.mysql.cj.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegistServlet", value = "/Manager/RegistServlet")
public class RegistServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        String pwd2 = request.getParameter("pwd2");
        if (StringUtils.isNullOrEmpty(username)||StringUtils.isNullOrEmpty(pwd)||StringUtils.isNullOrEmpty(pwd2)){
            writer.print(
                    AjaxResult.build()
                            .fail()
                            .setCode(AjaxResult.PARAMETER_EMPTY)
                            .setMsg("必要参数不能为空").toJsonString()
            );
            return;
        }
        if (!pwd.equals(pwd2)){
            writer.print(
                    AjaxResult.build()
                            .fail()
                            .setCode(AjaxResult.ERROR)
                            .setMsg("密码与确认密码不匹配").toJsonString()
            );
            return;
        }
        LoginService loginService = new LoginService();
        Manager manager = new Manager();
        manager.setId(username);
        manager.setName(null);
        manager.setPwd(pwd);
        boolean isRegisted = loginService.regist(manager);
        if (!isRegisted){
            writer.print(
                    AjaxResult.build()
                            .fail().setCode(AjaxResult.CONTAIN_USERNAME)
                            .setMsg("用户名已被注册").toJsonString()
            );
            return;
        }
        writer.print(
                AjaxResult.build()
                        .success()
                        .setMsg("注册成功").toJsonString()
        );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
