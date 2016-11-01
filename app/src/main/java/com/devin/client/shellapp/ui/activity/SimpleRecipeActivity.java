package com.devin.client.shellapp.ui.activity;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.devin.client.shellapp.R;
import com.devin.client.shellapp.model.Article;
import com.squareup.picasso.Picasso;

public class SimpleRecipeActivity extends AppCompatActivity {

    private  String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageView pic = (ImageView) findViewById(R.id.pic);
        TextView art = (TextView) findViewById(R.id.article);

        Bundle bundle=this.getIntent().getExtras();
        if(bundle.getSerializable("item")!=null && bundle.getSerializable("item") instanceof Article){
            Article article=(Article) bundle.getSerializable("item");
            title = article.getTitle();
            Picasso.with(this)
                    .load(article.getImageInt())
                    .into(pic);
            art.setText(article.getContext());
        }else{

        }
       
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(title);
        actionBar.setDisplayHomeAsUpEnabled(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "good tip!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    
}
