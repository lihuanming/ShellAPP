package com.devin.client.shellapp.utils;

/**
 * @author LHM
 * @version $Rev$
 * @time 2016/10/28 3:11
 * @des 回调接口
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */

public interface HttpResponeCallBack {
    public void onResponeStart(String apiName);

    /**
     * 此回调只有调用download方法下载数据时才生效
     *
     * @param apiName
     * @param count
     * @param current
     */

    public void onLoading(String apiName, long count, long current);

    public void onSuccess(String apiName, Object object);

    public void onFailure(String apiName, Throwable throwable, int errorNo, String strMsg);

}
