package com.bookmanager.servlet; /**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2022/6/7 22:25
 * @Created by 晨曦
 */

import com.bookmanager.pojo.Book;
import com.bookmanager.service.BookManageService;
import com.bookmanager.util.AjaxResult;
import com.mysql.cj.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "BookManageServlet", value = "/Manager/BookManage")
public class BookManageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AjaxResult ajaxResult = AjaxResult.build();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        String page = request.getParameter("page");
        String word = request.getParameter("word");//模糊查询
        String type = request.getParameter("type");//根据类型筛选
        BookManageService bookManageService = new BookManageService();
        List<Book> bookList = bookManageService.getBooks(page==null?1:Integer.parseInt(page),word,type);
        writer.print(
                ajaxResult
                        .success()
                        .setData(bookList)
                        .toJsonString()
        );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
