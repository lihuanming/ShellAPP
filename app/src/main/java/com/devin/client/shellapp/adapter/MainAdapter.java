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
import com.bigkoo.convenientbanner.OnItemClickListener;
import com.devin.client.shellapp.R;
import com.devin.client.shellapp.model.Article;
import com.devin.client.shellapp.model.HotRecipe;
import com.devin.client.shellapp.model.Store;
import com.devin.client.shellapp.ui.activity.SimpleRecipeActivity;
import com.devin.client.shellapp.ui.activity.SimpleTogatherActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
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

    public  MainAdapter(Context context, List<HotRecipe> recipes){
        this.context = context;
        this.hotRecipes = recipes;
    }

//    public MainAdapter(Context context,List<Image> images,List<Article> articles){
//        this.context=context;
//        this.articles = articles;
//        this.images=images;
//    }
    //private HotRecipes hotRecipes;

    private List<Integer> images = new ArrayList<>();
    //private List<Article> articles;
    //private List<Integer> image = new ArrayList<>();

    private  List<HotRecipe> hotRecipes;

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
        @Bind(R.id.cheer_1)
        ImageView cheer1;
        @Bind(R.id.cheer_2)
        ImageView cheer2;
        @Bind(R.id.cheer_3)
        ImageView cheer3;
        @Bind(R.id.new_shop)
        ImageView newShop;
        @Bind(R.id.youhui_shop)
        ImageView youhuiShop;
        public TopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            images.add(R.mipmap.togather_1);
            images.add(R.mipmap.togather_2);

            cheer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,SimpleRecipeActivity.class);
                    Bundle bundle = new Bundle();
                    Article article = new Article();
                    article.setImageInt(R.mipmap.cheer_1);
                    article.setTitle("巧克力蛋糕");
                    article.setContext(context.getResources().getString(R.string.qiaokelidangao));
                    bundle.putSerializable("item",article);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

            cheer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,SimpleRecipeActivity.class);
                    Bundle bundle = new Bundle();
                    Article article = new Article();
                    article.setImageInt(R.mipmap.cheer_2);
                    article.setTitle("甜甜圈");
                    article.setContext(context.getResources().getString(R.string.tiantianquan));
                    bundle.putSerializable("item",article);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

            cheer3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,SimpleRecipeActivity.class);
                    Bundle bundle = new Bundle();
                    Article article = new Article();
                    article.setImageInt(R.mipmap.cheer_4);
                    article.setTitle("马芬蛋糕");
                    article.setContext(context.getResources().getString(R.string.mafendangao));
                    bundle.putSerializable("item",article);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

            newShop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //// TODO: 16/10/27 商城
                }
            });

            youhuiShop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //// TODO: 16/10/27 优惠商城
                }
            });

            convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
                @Override
                public NetworkImageHolderView createHolder() {
                    return new NetworkImageHolderView();
                }
            }, images)
                    .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                    .setPageTransformer(ConvenientBanner.Transformer.DefaultTransformer)
                    .setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Store store = new Store();
                            switch (position){
                                case 0:
                                    store.setTitle("Awfully Chocolate(太古汇店)");
                                    store.setAddress("天河区天河路383号太古汇M楼50号铺(近天河路口)");
                                    store.setTel("15813078190");
                                    store.setImage(R.mipmap.togather_1);
                                    break;
                                case 1:
                                    store.setTitle("魔蝎座法式烘焙(正佳广场店)");
                                    store.setAddress("天河区天河路228号正佳广场2楼2A026A,2A026B号铺");
                                    store.setTel("15813078190");
                                    store.setImage(R.mipmap.togather_2);
                                    break;
                            }
                            Bundle bundle=new Bundle();
                            bundle.putSerializable("item",store);
                            Intent intent = new Intent(context,SimpleTogatherActivity.class);
                            intent.putExtras(bundle);
                            context.startActivity(intent);
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
        View view_top = LayoutInflater.from(context).inflate(R.layout.content_main_top, parent, false);
        View view_content = LayoutInflater.from(context).inflate(R.layout.cart_hot_recipe, parent, false);
        View view_empty = LayoutInflater.from(context).inflate(R.layout.emty_layout,parent,false);
        if (viewType == 0) return new TopViewHolder(view_top);
        else if(viewType == hotRecipes.size()+1){
            return new EmtpyViewHolder(view_empty);
        }
        else{
            view_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle=new Bundle();
                    Article article = new Article();
                    switch (viewType){
                        case 1:
                            article.setImageInt(R.mipmap.recipe_head_1);
                            article.setTitle("原味烤芝士");
                            article.setContext(context.getResources().getString(R.string.yuanweikaozhishi));
                            break;
                        case 2:
                            article.setImageInt(R.mipmap.recipe_header_2);
                            article.setTitle("黑加仑冻芝士");
                            article.setContext(context.getResources().getString(R.string.longstring));
                            break;
                        case 3:
                            article.setImageInt(R.mipmap.recipe_header_3);
                            article.setTitle("脆香曲奇");
                            article.setContext(context.getResources().getString(R.string.cuixiangquqi));
                            break;
                    }
                    Intent intent = new Intent(context, SimpleRecipeActivity.class);
                    bundle.putSerializable("item",article);
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
            ((HotViewHolder)holder).longstring.setText(hotRecipes.get(position-1).getLongstring());
            ((HotViewHolder)holder).name.setText(hotRecipes.get(position-1).getName());
            ((HotViewHolder)holder).date.setText(hotRecipes.get(position-1).getDate());
            Picasso.with(context)
                    .load(hotRecipes.get(position-1).getImgUrl())
                    .into(((HotViewHolder)holder).img);
        }
    }

    @Override
    public int getItemCount() {
        return hotRecipes.size() + 2;
    }

//    public class LocalImageHolderView implements CBPageAdapter.Holder<Integer> {
//        private ImageView imageView;
//
//        @Override
//        public View createView(Context context) {
//            imageView = new ImageView(context);
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            return imageView;
//        }
//
//        @Override
//        public void UpdateUI(Context context, final int position, Integer data) {
//            imageView.setImageResource(data);
//        }
//    }

    public class NetworkImageHolderView implements CBPageAdapter.Holder<Integer> {
        private ImageView imageView;
        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position,Integer image) {
            //Toast.makeText(context,image.getImage(),Toast.LENGTH_LONG).show();
            Picasso.with(context).load(image).into(this.imageView);
            // Picasso.with(context) .load("http://i.imgur.com/idojSYm.png").resize(50, 50).centerCrop().into(imageView);
        }
    }

}
