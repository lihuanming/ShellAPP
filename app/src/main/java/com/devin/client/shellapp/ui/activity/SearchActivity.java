package com.devin.client.shellapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.devin.client.shellapp.R;
import com.devin.client.shellapp.adapter.SearchAdapter;
import com.devin.client.shellapp.model.Search;
import com.devin.client.shellapp.model.Searchs;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author devin
 * @Class SearchActivity
 * @Date 16/10/28
 */

public class SearchActivity extends AppCompatActivity implements TextWatcher{

    @Bind(R.id.etSearch)
    EditText etSearch;
    @Bind(R.id.recyler)
    RecyclerView recyler;

    private SearchAdapter searchAdapter;

    private Searchs searchs = new Searchs();

    private List<Search> searcheList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        etSearch.addTextChangedListener(this);
        recyler.setLayoutManager(new LinearLayoutManager(this));
        searchAdapter = new SearchAdapter(this,searcheList);
        searchAdapter.setRecycleritemClickLisentner(new SearchAdapter.RecycleritemClickLisentner() {
            @Override
            public void onClick(Search search) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("item",search);
                Intent intent = new Intent(SearchActivity.this,SimpleSearchActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        recyler.setAdapter(searchAdapter);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        searcheList.clear();
        for (Search search : searchs.getSearches()){
            if (search.getContent().contains(etSearch.getText().toString()) || search.getTitle().contains(etSearch.getText().toString())){
                searcheList.add(search);
                Log.i("tag",searcheList.size() + "");
            }
        }
        searchAdapter.clear();
        searchAdapter.addAll(searcheList);
        searchAdapter.notifyDataSetChanged();
    }
}
