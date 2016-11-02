package com.devin.client.shellapp.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devin.client.shellapp.R;
import com.devin.client.shellapp.model.ProductsResponse;
import com.devin.client.shellapp.ui.activity.ProductsListActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author devin
 * @Class ProductsAdapter
 * @Date 16/11/1
 */

public class ProductsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ProductsListActivity productsListActivity ;

    private List<ProductsResponse.ResultEntity.ItemsEntity> itemsEntities = new ArrayList<>();

    private OnRecyclerItemOnClickListener onRecyclerItemOnClickListener;

    public ProductsAdapter(ProductsListActivity productsListActivity, List<ProductsResponse.ResultEntity.ItemsEntity> itemsEntities) {
        this.productsListActivity = productsListActivity;
        this.itemsEntities.addAll(itemsEntities);
    }

    public class ProductsViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.img)
        ImageView imageView;
        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.sell_point)
        TextView sellPoint;
        @Bind(R.id.money)
        TextView money;
        @Bind(R.id.card_view)
        CardView cardView;
        public ProductsViewHolder(View itemView) {
            super(itemView);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(productsListActivity).inflate(R.layout.recycler_item_product,parent,false);
        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ProductsViewHolder){
            ((ProductsViewHolder) holder).cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onRecyclerItemOnClickListener != null){
                        onRecyclerItemOnClickListener.onClick(position,itemsEntities.get(position));
                    }
                }
            });

            Picasso.with(productsListActivity).load(itemsEntities.get(position).getPic()).into(((ProductsViewHolder) holder).imageView);
            ((ProductsViewHolder) holder).name.setText(itemsEntities.get(position).getName());
            ((ProductsViewHolder) holder).money.setText(itemsEntities.get(position).getPrice() + "");
            ((ProductsViewHolder) holder).sellPoint.setText(itemsEntities.get(position).getSell_point());
        }
    }

    public void addAll(List<ProductsResponse.ResultEntity.ItemsEntity> list){
        itemsEntities.addAll(list);
    }

    public void clear(){
        itemsEntities.clear();
    }


    public void setOnRecyclerItemOnClickListener(OnRecyclerItemOnClickListener onRecyclerItemOnClickListener) {
        this.onRecyclerItemOnClickListener = onRecyclerItemOnClickListener;
    }

    public interface OnRecyclerItemOnClickListener{
        void onClick(int position,ProductsResponse.ResultEntity.ItemsEntity itemsEntity);
    }

    @Override
    public int getItemCount() {
        return itemsEntities.size();
    }
}
