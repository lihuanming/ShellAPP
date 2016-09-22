package com.devin.client.shellapp.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.devin.client.shellapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StartMainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.longstring)
    TextView longstring;
    @Bind(R.id.date)
    TextView date;
    @Bind(R.id.img)
    ImageView img;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.interesting)
    TextView interesting;
    @Bind(R.id.account)
    TextView account;
    @Bind(R.id.local)
    TextView local;
    @Bind(R.id.clike)
    RelativeLayout clike;
    @Bind(R.id.good_tip)
    ImageView goodTip;
    @Bind(R.id.good_tip_num)
    TextView goodTipNum;
    @Bind(R.id.share)
    ImageView share;
    @Bind(R.id.comment)
    ImageView comment;
    @Bind(R.id.comment_num)
    TextView commentNum;
    @Bind(R.id.card_view)
    CardView cardView;
    @Bind(R.id.empty)
    LinearLayout empty;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_main);
        ButterKnife.bind(this);
        empty.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardView.setVisibility(View.GONE);
                empty.setVisibility(View.VISIBLE);
            }
        });
    }

}
