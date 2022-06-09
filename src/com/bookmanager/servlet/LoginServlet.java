package com.bookmanager.servlet; /**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2022/6/7 20:44
 * @Created by 晨曦
 */

import com.alibaba.fastjson.JSONObject;
import com.bookmanager.service.LoginService;
import com.bookmanager.util.AjaxResult;
import com.mysql.cj.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

@WebServlet(name = "LoginServlet", value = "/Manager/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        String code = request.getParameter("code");
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        if (StringUtils.isNullOrEmpty(username) || StringUtils.isNullOrEmpty(pwd) || StringUtils.isNullOrEmpty(code)) {
            writer.print(
                    AjaxResult.build()
                            .fail()
                            .setCode(AjaxResult.PARAMETER_EMPTY)
                            .setMsg("必要参数不能为空").toJsonString()
            );
            return;
        }
        String realcode = (String) request.getSession().getAttribute("CheckCode");
        if (!code.equalsIgnoreCase(realcode)){
            writer.print(
                    AjaxResult.build()
                            .fail()
                            .setCode(AjaxResult.CHECKCODE_ERROR)
                            .setMsg("验证码错误").toJsonString()
            );
            return;
        }
        LoginService loginService = new LoginService();
        boolean isright = loginService.login(username, pwd,request.getSession());
        if (isright) {
            writer.write(
                    AjaxResult.build().success().setMsg("登录成功").toJsonString()
            );
        } else {
            writer.write(
                    AjaxResult.build().fail().setCode(AjaxResult.ERROR).setMsg("账号或密码有误").toJsonString()
            );
        }
    }
}

