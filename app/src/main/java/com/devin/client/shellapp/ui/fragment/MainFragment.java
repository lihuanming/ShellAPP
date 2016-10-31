package com.devin.client.shellapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.devin.client.shellapp.R;
import com.devin.client.shellapp.adapter.MainAdapter;
import com.devin.client.shellapp.model.HotRecipes;
import com.devin.client.shellapp.ui.activity.SearchActivity;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 书凡 on 2015-11-21.
 */
public class MainFragment extends Fragment {

    private String tag;
    private MainAdapter mainAdapter;
    @Bind(R.id.recycler)
    RecyclerView recycler;
    @Bind(R.id.search)
    ImageView search;

    private HotRecipes recipes = new HotRecipes();

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public MainFragment() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStop() {
        super.onStop();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView(){
        mainAdapter = new MainAdapter(getActivity(),recipes.getHotRecipes());
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(mainAdapter);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });
    }

    @OnClick(R.id.zxing)
    public void zxing(){
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        startActivityForResult(intent, 0);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
