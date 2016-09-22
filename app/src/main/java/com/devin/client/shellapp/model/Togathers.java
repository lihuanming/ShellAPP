package com.devin.client.shellapp.model;

import com.devin.client.shellapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 书凡 on 2015-11-17.
 */
public class Togathers {

    private List<Togather> togathers;

    public Togathers(){
        togathers = new ArrayList<>();
        togathers.add(getTogather(R.mipmap.togather_1,"Awfully Chocolate(太古汇店)","30感兴趣","40/人","天河区天河路383号太古汇M楼50号铺(近天河路口)","30","1"));
        togathers.add(getTogather(R.mipmap.togather_2,"摩羯座法式烘焙(正佳广场店)","10感兴趣","30/人","天河区天河路228号正佳广场2楼2A026A、2A026B号铺","24","20"));
        togathers.add(getTogather(R.mipmap.togather_3,"Simplylife CAFé BAKERY","20感兴趣","35/人","天河区天河路383号太古汇广场3楼天台","30","11"));
        togathers.add(getTogather(R.mipmap.togather_4,"Cheris Patisserie","30感兴趣","40/人","越秀区建设六马路1号","20","10"));
        togathers.add(getTogather(R.mipmap.togather_5,"L-ART","20感兴趣","30/人","天河区珠江新城华利路27号105号铺(保利香槟花园对面)","20","30"));
    }

    public List<Togather> getTogathers() {
        return togathers;
    }

    public void setTogathers(List<Togather> togathers) {
        this.togathers = togathers;
    }

    private Togather getTogather(int url,String name, String interesting,String account,String local,String goodtip,String comment){
        Togather togather = new Togather();
        togather.setAccount(account);
        togather.setImgUrl(url);
        togather.setGoodTipNum(goodtip);
        togather.setLocal(local);
        togather.setName(name);
        togather.setCommentNum(comment);
        togather.setInteresting(interesting);
         return togather;
    }

}
