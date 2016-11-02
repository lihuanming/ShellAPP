package com.devin.client.shellapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.devin.client.shellapp.R;
import com.devin.client.shellapp.adapter.ProductsAdapter;
import com.devin.client.shellapp.model.ProductsResponse;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author devin
 * @Class ProductsListActivity
 * @Date 16/11/1
 */

public class ProductsListActivity extends AppCompatActivity {

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recycler)
    RecyclerView recycler;

    private ProductsAdapter productsAdapter;

    private int cid;

    private int offset;

    private int limit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_shop_list);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
//        productsAdapter = new ProductsAdapter(this,)
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(productsAdapter);
        productsAdapter.setOnRecyclerItemOnClickListener(new ProductsAdapter.OnRecyclerItemOnClickListener() {
            @Override
            public void onClick(int position, ProductsResponse.ResultEntity.ItemsEntity itemsEntity) {
                Intent intent = new Intent(ProductsListActivity.this,ProductDetailActivity.class);

            }
        });

    }

}
