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
        searches.add(get(1,"interesting","good thing", R.mipmap.recipe_head_1,1));
        searches.add(get(1,"bad","bad thing", R.mipmap.recipe_header_2,2));
        searches.add(get(1,"yes","well", R.mipmap.recipe_header_3,1));
        searches.add(get(1,"yes d","xxxx", R.mipmap.recipe_header_4,2));
    }

    public List<Search> getSearches() {
        return searches;
    }

    private Search get(int id, String title, String content, int image, int type){
        Search search = new Search();
        search.setId(id);
        search.setImage(image);
        search.setContent(content);
        search.setTitle(title);
        search.setType(type);
        return search;
    }

}
