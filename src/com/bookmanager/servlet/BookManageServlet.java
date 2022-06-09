package com.bookmanager.servlet; /**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2022/6/7 22:25
 * @Created by 晨曦
 */

import com.bookmanager.pojo.Book;
import com.bookmanager.pojo.Manager;
import com.bookmanager.pojo.QueryObject;
import com.bookmanager.service.BookManageService;
import com.bookmanager.util.AjaxResult;
import com.mysql.cj.util.StringUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
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
        QueryObject<Book> bookList = bookManageService.getBooks(
                page==null?1:Long.parseLong(page),
                word,
                type
        );
        writer.print(
                ajaxResult
                        .success()
                        .setData(bookList)
                        .toJsonString()
        );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AjaxResult ajaxResult = AjaxResult.build();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        PrintWriter writer = response.getWriter();
        String action = request.getParameter("action");

        if (StringUtils.isNullOrEmpty(action)){
            writer.print(
                    ajaxResult
                            .fail()
                            .setCode(AjaxResult.PARAMETER_EMPTY)
                            .setMsg("action不能为空")
                            .toJsonString()
            );
            return;
        }
        BookManageService bookManageService = new BookManageService();
        Book book = new Book();
        try {
            BeanUtils.populate(book,request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if (action.equalsIgnoreCase("del")){
            if (book.getBookid()==null){
                writer.print(
                        ajaxResult
                                .fail()
                                .setCode(AjaxResult.PARAMETER_EMPTY)
                                .setMsg("bookId不能为空")
                                .toJsonString()
                );
                return;
            }
            boolean isDone = bookManageService.removeBook(book.getBookid());
            if (isDone) ajaxResult.success().setMsg("删除成功");
            else ajaxResult.fail().setMsg("该书本已被删除");
        }else if(action.equalsIgnoreCase("add")){
            if (!bookManageService.addBook(book)){
                ajaxResult.fail().setMsg("添加失败").setCode(AjaxResult.ERROR);
            }else ajaxResult.success().setMsg("添加成功");

        }else if(action.equalsIgnoreCase("update")){
            if (book.getBookid()==null){
                ajaxResult.fail().setMsg("必须填参数不能为空").setCode(AjaxResult.PARAMETER_EMPTY);
            }else {
                if(bookManageService.editBook(book)){
                    ajaxResult.success().setMsg("修改成功");
                }else {
                    ajaxResult.fail().setCode(AjaxResult.ERROR).setMsg("修改失败");
                }
            }
        }else {
            writer.print(
                    ajaxResult
                            .fail()
                            .setCode(AjaxResult.PARAMETER_ERROR)
                            .setMsg("非法ACTION参数")
                            .toJsonString()
            );
            return;
        }
        writer.print(ajaxResult.toJsonString());
    }
}
