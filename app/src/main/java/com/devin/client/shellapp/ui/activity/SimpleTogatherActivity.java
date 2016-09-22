package com.devin.client.shellapp.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.devin.client.shellapp.R;
import com.devin.client.shellapp.model.Store;
import com.squareup.picasso.Picasso;

public class SimpleTogatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_togather);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView pic = (ImageView)findViewById(R.id.pic);
        TextView title=(TextView)findViewById(R.id.title);
        TextView address=(TextView)findViewById(R.id.address);
        TextView tel=(TextView)findViewById(R.id.tel);

        Bundle bundle=this.getIntent().getExtras();
        final Store store=(Store)bundle.getSerializable("item");
        title.setText(""+store.getTitle());
        tel.setText("电话："+store.getTel());
        address.setText(store.getAddress());
        Picasso.with(this)
                .load(store.getImage())
                .into(pic);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                //Toast.makeText(getApplication(),store.getTel(),Toast.LENGTH_LONG).show();
                Uri data = Uri.parse("tel:"+store.getTel());
                intent.setData(data);
                startActivity(intent);
            }
        });
    }
}
