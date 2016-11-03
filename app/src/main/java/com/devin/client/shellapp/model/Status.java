package com.devin.client.shellapp.model;

/**
 * 项目名称：ShellApp
 * 类描述：
 * 创建人：LHM
 * 创建时间：2016/11/2 23:42
 * 修改人：LHM
 * 修改时间：2016/11/2 23:42
 * 修改备注：
 */

public class Status {
    public int code;
    public String success;
    public String message;
    public Result result;

    public Result getResult(){
        return result;
    }

    public void setResult(Result result){
        this.result = result;
    }

    public class Result{
        public String token;
        public void setToken(String token) {
            this.token = token;
        }
        public String getToken(){
            return token;
        }
    }

}
