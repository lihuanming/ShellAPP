package com.devin.client.shellapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.CBPageAdapter;
import com.bigkoo.convenientbanner.CBViewHolderCreator;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.devin.client.shellapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 书凡 on 2015-11-29.
 */
public class ShoppingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Integer> image = new ArrayList<>();

    private Context context;

    public ShoppingAdapter(Context context) {
        this.context = context;
    }

    class ShppingAllViewHolder extends RecyclerView.ViewHolder {
        public ShppingAllViewHolder(View itemView) {
            super(itemView);
        }
    }

    class EmtpyViewHolder extends RecyclerView.ViewHolder {
        public EmtpyViewHolder(View itemView) {
            super(itemView);
        }
    }

    class ShoppingTopViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.convenientBanner)
        ConvenientBanner convenientBanner;
        public ShoppingTopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            image.add(R.mipmap.cheer_1);
            image.add(R.mipmap.cheer_2);
            image.add(R.mipmap.cheer_4);

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

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View topView = LayoutInflater.from(context).inflate(R.layout.content_shopping_top, parent, false);
        View shopAllView = LayoutInflater.from(context).inflate(R.layout.content_shop_all,parent,false);
        View emtpyView = LayoutInflater.from(context).inflate(R.layout.emty_layout,parent,false);
        if (viewType == 0) return new ShoppingTopViewHolder(topView);
        else if (viewType == 1) return new ShppingAllViewHolder(shopAllView);
        else if(viewType == 2) return new EmtpyViewHolder(emtpyView);
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
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
