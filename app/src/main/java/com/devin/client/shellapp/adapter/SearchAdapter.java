package com.devin.client.shellapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.devin.client.shellapp.R;
import com.devin.client.shellapp.model.Search;
import com.devin.client.shellapp.ui.activity.SearchActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author devin
 * @Class SearchAdapter
 * @Date 16/10/28
 */

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Search> searchList = new ArrayList<>();
    private SearchActivity searchActivity;
    private RecycleritemClickLisentner recycleritemClickLisentner;

    public SearchAdapter(SearchActivity searchActivity,List<Search> searches){
        this.searchActivity = searchActivity;
        searchList.addAll(searches);
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.title)
        TextView textView;
        @Bind(R.id.content)
        TextView content;
        @Bind(R.id.linear)
        LinearLayout linearLayout;
        public SearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchViewHolder(LayoutInflater.from(searchActivity).inflate(R.layout.recycler_item_search,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((SearchViewHolder)holder).textView.setText(searchList.get(position).getTitle());
        ((SearchViewHolder)holder).content.setText(searchList.get(position).getContent());
        if (recycleritemClickLisentner != null){
            ((SearchViewHolder)holder).linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recycleritemClickLisentner.onClick(searchList.get(position));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    public void addAll(List<Search> searches){
        searchList.addAll(searches);
    }

    public void clear(){
        searchList.clear();
    }

    public interface RecycleritemClickLisentner{
        void onClick(Search search);
    }



    public void setRecycleritemClickLisentner(RecycleritemClickLisentner recycleritemClickLisentner) {
        this.recycleritemClickLisentner = recycleritemClickLisentner;
    }
}
