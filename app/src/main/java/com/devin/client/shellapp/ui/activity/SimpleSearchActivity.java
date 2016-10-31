package com.devin.client.shellapp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.devin.client.shellapp.R;
import com.devin.client.shellapp.model.Search;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author devin
 * @Class SimpleSearchActivity
 * @Date 16/10/28
 */

public class SimpleSearchActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.shop_img)
    ImageView shopImg;
    @Bind(R.id.shop_detail)
    TextView shopDetail;
    @Bind(R.id.linear)
    LinearLayout linear;
    @Bind(R.id.sub)
    ImageView sub;
    @Bind(R.id.add)
    ImageView add;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_search);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("商品详情");
        Search search =  (Search) getIntent().getSerializableExtra("item");
        if (search != null){
            Picasso.with(this).load(search.getImage()).into(shopImg);
            shopDetail.setText(search.getTitle()+"\n\n" + search.getContent());
        }

    }
}
