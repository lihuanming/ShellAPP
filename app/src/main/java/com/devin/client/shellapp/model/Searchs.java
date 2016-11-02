package com.devin.client.shellapp.model;

import com.devin.client.shellapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author devin
 * @Class Searchs
 * @Date 16/10/28
 */

public class Searchs {
    private List<Search> searches;

    public Searchs(){
        searches = new ArrayList<>();
        searches.add(get(1,"原味烤芝士","奶油奶酪150g，低筋粉15g，玉米淀粉10g，精制砂糖30克，纯牛奶60g，白砂糖50g，黄油30g，鸡蛋3只", R.mipmap.recipe_head_1));
        searches.add(get(2,"黑加仑冻芝士","麦维他纤维饼干100克，无盐黄油45克，奶油奶酪200克，精制砂糖30克，吉利丁片两片，淡奶油160克，牛奶100毫升，六寸圆模型一个，黑加仑酱适量（按照个人口味添加）", R.mipmap.recipe_header_2));
        searches.add(get(3,"脆香曲奇","黄油117g 牛奶30g 白砂糖45g 低粉146g 盐0.3g 杏仁粉30g.", R.mipmap.recipe_header_3));
        searches.add(get(4,"黑森林","低筋面粉75g 牛奶68g 糖88g 鸡蛋5个 玉米油50g 可可粉20g 装饰用车厘子罐头适量 鲜奶油300ml左右 巧克力150g", R.mipmap.recipe_header_4));
        searches.add(get(5,"牛角","牛奶120g 鸡蛋1个 玉米油30g 白糖30g 普道面粉200 酵母5g", R.mipmap.recipe_header_5));
        searches.add(get(6,"巧克力","黑巧克力180g 白朗姆酒20ml 黄油18g 白糖7g 纯净水12ml.", R.mipmap.recipe_header_6));
    }

    public List<Search> getSearches() {
        return searches;
    }

    private Search get(int id, String title, String content, int image){
        Search search = new Search();
        search.setId(id);
        search.setImage(image);
        search.setContent(content);
        search.setTitle(title);
        return search;
    }

}
