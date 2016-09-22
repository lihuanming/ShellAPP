package com.devin.client.shellapp.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devin.client.shellapp.R;
import com.devin.client.shellapp.adapter.RecipeAdapter;
import com.devin.client.shellapp.model.Recipes;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 书凡 on 2015-11-18.
 */
public class RecipeFragment extends Fragment {


    RecipeAdapter recipeAdapter;

    @Bind(R.id.recycler)
    RecyclerView recycler;

    public static RecipeFragment newInstance() {
        RecipeFragment fragment = new RecipeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public RecipeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe, container, false);
        ButterKnife.bind(this, rootView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recipeAdapter = new RecipeAdapter(getActivity(), new Recipes());

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return recipeAdapter.isHeader(position) ? 2 : 1;
            }
        });

        recycler.setLayoutManager(gridLayoutManager);

        recycler.setAdapter(recipeAdapter);

        return rootView;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
