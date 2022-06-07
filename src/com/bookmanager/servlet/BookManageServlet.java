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

@WebServlet(name = "BookManageServlet", value = "/BookManageServlet")
public class BookManageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AjaxResult ajaxResult = AjaxResult.build();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
/*        String offset = request.getParameter("offset");
        if (StringUtils.isNullOrEmpty(offset)){
            writer.print(
                    ajaxResult
                    .fail()
                    .setMsg("必要参数不能为空！")
                    .toJsonString()
            );
            return;
        }*/
        BookManageService bookManageService = new BookManageService();
        List<Book> bookList = bookManageService.getBooks();
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
