package com.bookmanager.servlet; /**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2022/6/8 19:30
 * @Created by 晨曦
 */

import com.bookmanager.util.VCCUtil;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet(name = "CheckCodeServlet", value = "/CheckCode")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("image/jpeg;");
        int width = 200;
        int height = 50;
        //获取验证码对象
        VCCUtil vccUtil = new VCCUtil();
        BufferedImage image = vccUtil.generateCode(width, height);
        String code = vccUtil.getCode();
        request.getSession().setAttribute("CheckCode",code);
        //将图片输入到页面
        ImageIO.write(image, "jpg", response.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
