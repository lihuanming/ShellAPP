package com.devin.client.shellapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.CBPageAdapter;
import com.bigkoo.convenientbanner.CBViewHolderCreator;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.devin.client.shellapp.R;
import com.devin.client.shellapp.model.Article;
import com.devin.client.shellapp.model.Image;
import com.devin.client.shellapp.ui.activity.SimpleRecipeActivity;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 书凡 on 2015-11-27.
 */
public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    public MainAdapter(Context context) {
        this.context = context;
    }

    public MainAdapter(Context context,List<Image> images,List<Article> articles){
        this.context=context;
        this.articles = articles;
        this.images=images;
    }
    //private HotRecipes hotRecipes;

    private List<Image> images;
    private List<Article> articles;
    //private List<Integer> image = new ArrayList<>();

    public class EmtpyViewHolder extends RecyclerView.ViewHolder {

        public EmtpyViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class HotViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.img)
        ImageView img;
        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.longstring)
        TextView longstring;
        @Bind(R.id.date)
        TextView date;

        public HotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class TopViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.convenientBanner)
        ConvenientBanner convenientBanner;

        public TopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
           /* image.add(R.mipmap.togather_1);
            image.add(R.mipmap.togather_2);
            image.add(R.mipmap.togather_3);*/

            //TODO
            convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
                @Override
                public NetworkImageHolderView createHolder() {
                    return new NetworkImageHolderView();
                }
            }, images)
                    .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                    .setPageTransformer(ConvenientBanner.Transformer.DefaultTransformer);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view_top = LayoutInflater.from(context).inflate(R.layout.content_main_top, parent, false);
        View view_content = LayoutInflater.from(context).inflate(R.layout.cart_hot_recipe, parent, false);
        View view_empty = LayoutInflater.from(context).inflate(R.layout.emty_layout,parent,false);
        if (viewType == 0) return new TopViewHolder(view_top);
        else if(viewType == articles.size()+1){
            return new EmtpyViewHolder(view_empty);
        }
        else{
            view_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SimpleRecipeActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("item",articles.get(viewType-1));
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
            return new HotViewHolder(view_content);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HotViewHolder) {
            ((HotViewHolder)holder).longstring.setText(articles.get(position-1).getContext());
            ((HotViewHolder)holder).name.setText(articles.get(position-1).getTitle());
            ((HotViewHolder)holder).date.setText(articles.get(position-1).getCreated());
            Picasso.with(context)
                    .load(articles.get(position-1).getImages())
                    .into(((HotViewHolder)holder).img);
        }
    }

    @Override
    public int getItemCount() {
        return articles.size() + 2;
    }

    public class LocalImageHolderView implements CBPageAdapter.Holder<Integer> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, Integer data) {
            imageView.setImageResource(data);
        }
    }

    public class NetworkImageHolderView implements CBPageAdapter.Holder<Image> {
        private ImageView imageView;
        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position,Image image) {
            //Toast.makeText(context,image.getImage(),Toast.LENGTH_LONG).show();
            Picasso.with(context).load(image.getImage()).into(this.imageView);
            // Picasso.with(context) .load("http://i.imgur.com/idojSYm.png").resize(50, 50).centerCrop().into(imageView);
        }
    }

}
