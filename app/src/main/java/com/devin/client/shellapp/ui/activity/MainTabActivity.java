package com.devin.client.shellapp.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.devin.client.shellapp.R;
import com.devin.client.shellapp.entity.TabEntity;
import com.devin.client.shellapp.ui.fragment.EyeFragment;
import com.devin.client.shellapp.ui.fragment.MainFragment;
import com.devin.client.shellapp.ui.fragment.RecipeFragment;
import com.devin.client.shellapp.ui.fragment.ShoppingFragment;
import com.devin.client.shellapp.ui.fragment.UserInfoFragment;
import com.devin.client.shellapp.utils.ViewFindUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

public class MainTabActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;// 定义SharedPreferences，用于保存密码
    public static ArrayList<Fragment> fragments2 = new ArrayList<>();
    public static  ImageView line;
    private int cur_index;
    public static CommonTabLayout commonTabLayout;
    private String[] titles = {"首页", "商城", "星级食谱", "贝壳圈", "我"};
    private int[] iconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_shopping_unselect,
            R.mipmap.tab_book_unselect, R.mipmap.tab_eye_unselect,
            R.mipmap.tab_me_unselect

    };
    private int[] iconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_shopping_select,
            R.mipmap.tab_book_select, R.mipmap.tab_eye_select,
            R.mipmap.tab_me_select
    };
    private ArrayList<CustomTabEntity> tabs = new ArrayList<>();
    private View decorView;
    public static CommonTabLayout tabMain;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(cur_index==4 && keyCode==KeyEvent.KEYCODE_BACK){
            commonTabLayout.setVisibility(View.VISIBLE);
            line.setVisibility(View.VISIBLE);
            tabMain.setCurrentTab(0);
            cur_index=0;
            return false;
        }else{
            return super.onKeyDown(keyCode,event);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_tab);
        commonTabLayout = (CommonTabLayout)findViewById(R.id.tab_main);
        line =(ImageView)findViewById(R.id.line);
        sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        fragments2.add(MainFragment.newInstance());
        fragments2.add(ShoppingFragment.newInstance());
        fragments2.add(RecipeFragment.newInstance());
        fragments2.add(EyeFragment.newInstance());
        fragments2.add(UserInfoFragment.newInstance());
       /* if(sharedPreferences.getString("account",null)==null){
            fragments2.add(nologinFragment.newInstance());
        }else{
            fragments2.add(UserInfoFragment.newInstance());
        }*/
    
        for (int i = 0; i < titles.length; i++) {
            tabs.add(new TabEntity(titles[i], iconSelectIds[i], iconUnselectIds[i]));
        }

        decorView = getWindow().getDecorView();

        /** with ViewPager */
        tabMain = ViewFindUtils.find(decorView, R.id.tab_main);

        tabMain.setTabData(tabs, getSupportFragmentManager(), R.id.fl_change, fragments2);

        tabMain.setCurrentTab(0);

        tabMain.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position == 4) {
                    cur_index = 4;
//                    commonTabLayout.setVisibility(View.INVISIBLE);
//                    line.setVisibility(View.INVISIBLE);
                    // 判断账号密码是否空
                   /* if (account != null && password != null) {
                        //fragments2.remove(4);
                    } else{
                        commonTabLayout.setVisibility(View.VISIBLE);
                        line.setVisibility(View.VISIBLE);
                        //fragments2.set(4, UserInfoFragment.newInstance());
                        fragments2.clear();
                        fragments2.add(MainFragment.newInstance());
                        fragments2.add(ShoppingFragment.newInstance());
                        fragments2.add(RecipeFragment.newInstance());
                        fragments2.add(EyeFragment.newInstance());
                        fragments2.add(UserInfoFragment.newInstance());
                        tabMain.setTabData(tabs, getSupportFragmentManager(), R.id.fl_change, fragments2);
                        Toast.makeText(MainTabActivity.this,"dddd",Toast.LENGTH_LONG).show();
                        
                    }*/
                }
                
            }
            
            @Override
            public void onTabReselect(int position) {

            }
        });
        //fragments2.add(UserInfoFragment.newInstance());
    }


}
