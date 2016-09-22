package com.devin.client.shellapp.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.devin.client.shellapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UserMainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.img)
    ImageView img;
    @Bind(R.id.name)
    TextView name;
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
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.empty)
    LinearLayout empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
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
