package com.devin.client.shellapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.devin.client.shellapp.R;
import com.devin.client.shellapp.model.Store;
import com.devin.client.shellapp.ui.activity.SimpleTogatherActivity;
import com.devin.client.shellapp.utils.ShareUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 书凡 on 2015-11-17.
 */
public class TogatherAdatper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    private List<Store> stores;
    //private Togathers togathers;

    public TogatherAdatper(Context context,List<Store> stores) {
        this.context = context;
        this.stores = stores;
    }

    public class SloganViewHolder extends RecyclerView.ViewHolder {
        public SloganViewHolder(View itemView) {
            super(itemView);
        }
    }


    public class TogatherViewHolder extends RecyclerView.ViewHolder {
        private int index;
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
        @Bind(R.id.comment_num)
        TextView commentNum;
        @Bind(R.id.good_tip)
        ImageView goodTip;
        @Bind(R.id.good_tip_num)
        TextView goodTipNum;
        @Bind(R.id.share)
        ImageView share;

        @Bind(R.id.clike)
        RelativeLayout clike;

        public TogatherViewHolder(View itemView, final int index) {
            super(itemView);
            this.index = index;
            ButterKnife.bind(this, itemView);
            clike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context,index+"",Toast.LENGTH_LONG).show();
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("item",stores.get(index));
                    Intent intent = new Intent(context,SimpleTogatherActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShareUtils.share(context);
                }
            });

            goodTip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goodTip.setImageResource(R.mipmap.goodtip_select);
                    int num = Integer.valueOf(goodTipNum.getText().toString());
                    num++;
                    goodTipNum.setText(num + "");
                }
            });

        }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.card_view_togather, parent, false);

        View viewSlogan = LayoutInflater.from(context).inflate(R.layout.emty_layout, parent, false);

        if (viewType == stores.size()) {
            return new SloganViewHolder(viewSlogan);
        } else {
            return new TogatherViewHolder(view,viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TogatherViewHolder) {
            ((TogatherViewHolder) holder).name.setText(stores.get(position).getTitle());
            ((TogatherViewHolder) holder).interesting.setText(stores.get(position).getInterest()+"人感兴趣");
            ((TogatherViewHolder) holder).local.setText(stores.get(position).getAddress());
            ((TogatherViewHolder) holder).commentNum.setText(stores.get(position).getComment()+"");
            ((TogatherViewHolder) holder).account.setText(stores.get(position).getNumber()+"/人");
            ((TogatherViewHolder) holder).goodTipNum.setText(stores.get(position).getCollect()+"");
            Picasso.with(context).load(stores.get(position).getImage())
                    .into(((TogatherViewHolder) holder).img);
        }
    }

    @Override
    public int getItemCount() {
        return stores.size() + 1;
    }
}
