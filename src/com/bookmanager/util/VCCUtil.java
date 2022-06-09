package com.bookmanager.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 生成验证码
 */
public class VCCUtil {
    public final static String codes = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
    //用于获取每次生成的验证码
    private String code ="";
    //无参构造
    public VCCUtil() {
    }

    /**
     * @return 验证码
     */
    public String getCode() {
        return code;
    }

    /**
     * @param width 元素的宽
     * @param height 元素的高
     * @return BufferedImage 验证码图片
     */
    public  BufferedImage generateCode(int width, int height){
        //创建图片对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        //美化图片
        //通过图片对象下的方法获取画笔对象
        Graphics gp = image.getGraphics();
        //设置画笔的颜色
        gp.setColor(Color.PINK);
        //使用画笔填充的方法
        gp.fillRect(0,0,width,height);
        //使用画笔的绘画方法
        gp.setColor(Color.orange);
        gp.drawRect(0,0,width-1,height-1);
        //使用画笔的绘画方法画字符串(写验证码)
        Random ran = new Random();
        //循环写四个验证码
        for (int i = 1; i <=4 ; i++) {
            gp.setColor(Color.BLUE);
            int index = ran.nextInt(codes.length());
            int yAxis = ran.nextInt(5)+2;
            char ch = codes.charAt(index);//随机取字符
            gp.setFont(new Font("Tahoma", Font.BOLD, 30));
            gp.drawString(Character.toString(ch),width/6*i+yAxis,height/2+yAxis);
            this.code += ch;//储存生成的验证码
        }
        //设置干扰线
        for (int i = 1; i <3 ; i++) {
            gp.setColor(Color.RED);
            int x1 = ran.nextInt(20)+10;
            int xmax = ran.nextInt(width-100+1)+100;
            int y1 = ran.nextInt(20)+10;
            int ymax = ran.nextInt(height-20+1)+10;
            gp.drawLine(x1,y1,xmax,ymax);
        }
        return image;
    }
}