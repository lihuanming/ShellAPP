package com.devin.client.shellapp.model;

import com.devin.client.shellapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 书凡 on 2015-11-18.
 */
public class Recipes {


    private List<Recipe> recipes;

    public Recipes(){
        recipes = new ArrayList<>();
        recipes.add(getRecipe(R.mipmap.recipe_head_1,"原味烤芝士"));
        recipes.add(getRecipe(R.mipmap.recipe_header_2,"黑加仑冻芝士"));
        recipes.add(getRecipe(R.mipmap.recipe_header_3,"脆香曲奇"));
        recipes.add(getRecipe(R.mipmap.recipe_header_4,"黑森林"));
        recipes.add(getRecipe(R.mipmap.recipe_header_5,"牛角"));
        recipes.add(getRecipe(R.mipmap.recipe_header_6,"巧克力"));
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    private Recipe getRecipe(int URL, String name){
        Recipe recipe = new Recipe();
        recipe.setImgUrl(URL);
        recipe.setName(name);
        return recipe;
    }

}
