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
import com.devin.client.shellapp.model.Recipes;
import com.devin.client.shellapp.ui.activity.SimpleRecipeActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 书凡 on 2015-11-18.
 */
public class RecipeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_VIEW_TYPE_HEADER = 0;


    private List<Integer> image = new ArrayList<>();

    private Context context;

    private Recipes recipes;

    public RecipeAdapter(Context context, Recipes recipes) {
        this.context = context;
        this.recipes = recipes;
    }

    public class EmtpyViewHolder extends RecyclerView.ViewHolder {

        public EmtpyViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.convenientBanner)
        ConvenientBanner convenientBanner;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            image.add(R.mipmap.recipe_header_1);
            image.add(R.mipmap.togather_2);

            convenientBanner.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
                @Override
                public LocalImageHolderView createHolder() {
                    return new LocalImageHolderView();
                }
            }, image)
                    .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                    .setPageTransformer(ConvenientBanner.Transformer.DefaultTransformer);
        }
    }

    public boolean isHeader(int position) {
        return position == 0;
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.img)
        ImageView img;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return isHeader(position) ? ITEM_VIEW_TYPE_HEADER : position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view_top = LayoutInflater.from(context).inflate(R.layout.content_recipe_header, parent, false);
            return new HeaderViewHolder(view_top);
        }else if (viewType == recipes.getRecipes().size() + 1){
            View view = LayoutInflater.from(context).inflate(R.layout.emty_layout,parent,false);
            return new EmtpyViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(context).inflate(R.layout.card_view_recipe, parent, false);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SimpleRecipeActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("item","test");
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
            return new RecipeViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RecipeViewHolder) {
            ((RecipeViewHolder) holder).name.setText(recipes.getRecipes().get(position-1).getName());
            Picasso.with(context)
                    .load(recipes.getRecipes().get(position-1).getImgUrl())
                    .into(((RecipeViewHolder) holder).img);
        }
    }

    @Override
    public int getItemCount() {
        return recipes.getRecipes().size() + 2;
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
}
