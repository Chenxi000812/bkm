package com.bookmanager.servlet; /**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2022/6/7 20:44
 * @Created by 晨曦
 */

import com.alibaba.fastjson.JSONObject;
import com.bookmanager.service.LoginService;
import com.bookmanager.util.AjaxResult;

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
            response.setHeader("contentType", "text/html; charset=utf-8");
            PrintWriter writer = response.getWriter();
            String code = request.getParameter("code");
            String username = request.getParameter("username");
            String pwd = request.getParameter("pwd");
            LoginService loginService = new LoginService();
            boolean isright = loginService.login(username, pwd);
            if (isright) {
                writer.write(
                        AjaxResult.build().success().setMsg("登录成功").toJsonString()
                );
            } else {
                writer.write(
                        AjaxResult.build().fail().setMsg("账号或密码有误").toJsonString()
                );
            }
        }

        @Override
        protected void doPost (HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {

                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
                out.println("<HTML>");
                out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
                out.println("  <BODY>");
                out.print("    This is ");
                out.print(this.getClass());
                out.println(", using the POST method");
                out.println("  </BODY>");
                out.println("</HTML>");
                out.flush();
                out.close();
            }
        }

