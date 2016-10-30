package com.devin.client.shellapp.model;

import java.io.Serializable;

/**
 * 项目名称：ShellApp
 * 类描述：解析注册接口数据
 * 创建人：LHM
 * 创建时间：2016/10/28 5:01
 * 修改人：LHM
 * 修改时间：2016/10/28 5:01
 * 修改备注：
 */

public class AnalyticalRegistInfo implements Serializable{
    private String ret;//0 ：数据返回成功 1 ：数据返回失败
    private String errcode;// errcode错误码类型
    private String msg;//错误信息
    private String name;//昵称
    private String userhead;//用户头像路径
    private int userid;//用户id
    private String email;//用户邮箱


    public String getRet() {
        return ret;
    }
    public void setRet(String ret) {
        this.ret = ret;
    }
    public String getErrcode() {
        return errcode;
    }
    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUserhead() {
        return userhead;
    }
    public void setUserhead(String userhead) {
        this.userhead = userhead;
    }
    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
