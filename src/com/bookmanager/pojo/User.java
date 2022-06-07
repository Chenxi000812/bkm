package com.bookmanager.pojo;

/**
 * @Classname User
 * @Description TODO
 * @Date 2022/6/7 21:11
 * @Created by 晨曦
 */
public class User {
    private String userid;
    private String username;
    private String pwd;
    private String sex;
    private String zhiwu;
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getZhiwu() {
        return zhiwu;
    }
    public void setZhiwu(String zhiwu) {
        this.zhiwu = zhiwu;
    }
}
