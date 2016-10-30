package com.devin.client.shellapp.model;

import java.io.Serializable;

/**
 * 项目名称：ShellApp
 * 类描述：解析获取用户基本信息
 * 创建人：LHM
 * 创建时间：2016/10/28 14:50
 * 修改人：LHM
 * 修改时间：2016/10/28 14:50
 * 修改备注：
 */
public class UserBaseInfo implements Serializable{
    //	 {"ret":"0","errcode":"0","msg":"接口调用成功","name":"erom","userhead":"/img/users/head/avatar.png",
    //	"userid":"11653","email":"123456789@qq.com","role":"0"}
    private String userid;//用户id
    private String name;//昵称
    private String userhead;//用户头像路径
    private String email;//用户邮件
    private String ret;//请求状态码
    private String errcode;//错误码
    private String msg;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
}
