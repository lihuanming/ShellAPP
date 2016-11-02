package com.devin.client.shellapp.utils;

import com.devin.client.shellapp.R;
import com.devin.client.shellapp.model.HotRecipe;
import com.devin.client.shellapp.model.Recipe;
import com.devin.client.shellapp.model.Search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author devin
 * @Class AllSimpleListUtils
 * @Date 16/10/31
 */

public class AllSimpleListUtils {

    //搜索的
    private static List<Search> searches = new ArrayList<>();

    //菜谱的
    private static List<Recipe> recipes = new ArrayList<>();

    //首页的菜谱列表
    private static List<HotRecipe> hotRecipes = new ArrayList<>();


    public static List<Search> getSearches(){
        searches.add(get(1,"interesting","good thing", R.mipmap.recipe_head_1,1));
        searches.add(get(1,"bad","bad thing", R.mipmap.recipe_header_2,2));
        searches.add(get(1,"yes","well", R.mipmap.recipe_header_3,1));
        searches.add(get(1,"yes d","xxxx", R.mipmap.recipe_header_4,2));
        return searches;
    }

    private static Search get(int id, String title, String content, int image, int type){
        Search search = new Search();
        search.setId(id);
        search.setImage(image);
        search.setContent(content);
        search.setTitle(title);
        return search;
    }

    public static List<Recipe> getRecipes(){
        recipes.add(getRecipe(1,R.mipmap.recipe_head_1,"原味烤芝士"));
        recipes.add(getRecipe(2,R.mipmap.recipe_header_2,"黑加仑冻芝士"));
        recipes.add(getRecipe(3,R.mipmap.recipe_header_3,"脆香曲奇"));
        recipes.add(getRecipe(4,R.mipmap.recipe_header_4,"黑森林"));
        recipes.add(getRecipe(5,R.mipmap.recipe_header_5,"牛角"));
        recipes.add(getRecipe(6,R.mipmap.recipe_header_6,"巧克力"));
        return recipes;
    }

    private static Recipe getRecipe(int id,int URL, String name){
        Recipe recipe = new Recipe();
        recipe.setImgUrl(URL);
        recipe.setName(name);
        return recipe;
    }
}
