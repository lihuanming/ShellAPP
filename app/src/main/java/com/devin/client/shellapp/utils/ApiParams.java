package com.devin.client.shellapp.utils;

import java.util.HashMap;

/**
 * 项目名称：ShellApp
 * 类描述：接口参数
 * 创建人：LHM
 * 创建时间：2016/10/28 4:54
 * 修改人：LHM
 * 修改时间：2016/10/28 4:54
 * 修改备注：
 */

public class ApiParams extends HashMap<String, String> {
    private static final long serialVersionUID = 8112047472727256876L;

    public ApiParams with(String key, String value) {
        put(key, value);
        return this;
    }
}
