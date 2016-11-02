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
import com.devin.client.shellapp.model.Search;
import com.squareup.picasso.Picasso;

import static com.devin.client.shellapp.R.id.article;

/**
 * @author devin
 * @Class SimpleSearchActivity
 * @Date 16/10/28
 */

public class SimpleSearchActivity extends AppCompatActivity {

    private  String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageView pic = (ImageView) findViewById(R.id.pic);
        TextView art = (TextView) findViewById(article);

        Bundle bundle=this.getIntent().getExtras();
        if(bundle.getSerializable("item")!=null && bundle.getSerializable("item") instanceof Search){
            Search search=(Search) bundle.getSerializable("item");
            title = search.getTitle();
            Picasso.with(this)
                    .load(search.getImage())
                    .into(pic);
            art.setText(getContent(search.getId()));
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

    private String getContent(int postition){
        String content = "";
        switch (postition){
            case 1:
                content = getResources().getString(R.string.yuanweikaozhishi);
                break;
            case 2:
                content = getResources().getString(R.string.longstring);
                break;
            case 3:
                content = getResources().getString(R.string.cuixiangquqi);
                break;
            case 4:
                content = getResources().getString(R.string.heisenlin);
                break;
            case 5:
                content = getResources().getString(R.string.niujiao);
                break;
            case 6:
                content = getResources().getString(R.string.qiaokeli);
        }

        return content;
    }
}
