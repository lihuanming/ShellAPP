package com.devin.client.shellapp.model;

import com.devin.client.shellapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 书凡 on 2015-11-27.
 */
public class HotRecipes {

    private List<HotRecipe> hotRecipes;

    public HotRecipes() {
        hotRecipes = new ArrayList<>();
        hotRecipes.add(getHotRecipe(R.mipmap.recipe_head_1, "原味烤芝士", "芝士又名奶酪、干酪，指动物乳经乳酸菌发酵或加酶后凝固，并除去乳清制成的浓缩乳制品。芝士蛋糕就是用它做的。原味芝士蛋糕属于糕点/甜点，主要原料是白面、起司，口味是甜，...", "2015-11-20 10:22"));
        hotRecipes.add(getHotRecipe(R.mipmap.recipe_header_2, "黑加仑冻芝士", "用酸奶油代替高脂厚奶油,这使得芝士蛋糕更容易冻结,这也是大部分冻芝士蛋糕的...最典型的有:黑莓,黑加仑,草莓,覆盆子以及柠檬...", "2015-11-26 17:28"));
        hotRecipes.add(getHotRecipe(R.mipmap.recipe_header_3, "脆香曲奇", "香酥脆曲奇的做法,学会了怎么做香酥脆曲奇。查看关于香酥脆曲奇的用料、步骤图、注意事项等信息,以及用什么菜和香酥脆曲奇搭配...", "2015-11-21 20:16"));
    }

    public List<HotRecipe> getHotRecipes(){
        return hotRecipes;
    }

    private HotRecipe getHotRecipe(int img, String name,String longstring,String date){
        HotRecipe hotRecipe = new HotRecipe();
        hotRecipe.setImgUrl(img);
        hotRecipe.setName(name);
        hotRecipe.setDate(date);
        hotRecipe.setLongstring(longstring);
        return hotRecipe;
    }
}
