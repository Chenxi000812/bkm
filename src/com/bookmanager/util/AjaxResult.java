package com.bookmanager.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @Classname AjaxResult
 * @Description 用来返回数据的
 * @Date 2022/6/7 22:45
 * @Created by 晨曦
 */
public class AjaxResult {
    public static final String ERROR = "40000";
    public static final String CONTAIN_USERNAME = "40003";
    public static final String PARAMETER_EMPTY = "40001";
    public static final String PARAMETER_ERROR = "40002";
    private boolean success; //业务请求的是否成功 例如：登录成功 true   失败 false
    private String msg; //消息内容
    private String code; //错误代码 例如: 4000 密码错误  4001参数有误  5000后端bug
    private Object data; //返回给前端的具体数据 可空  一般用于查询出的内容
    public AjaxResult success(){
        this.success = true;
        return this;
    }
    public AjaxResult fail(){
        this.success = false;
        return this;
    }
    public AjaxResult setCode(String code){
        this.code = code;
        return this;
    }
    public AjaxResult setData(Object data){
        this.data = data;
        return this;
    }
    public AjaxResult setMsg(String msg){
        this.msg = msg;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }
    public String toJsonString(){
        return JSONObject.toJSONString(this);
    }
    public static AjaxResult build(){
        return new AjaxResult();
    }

}
