package com.devin.client.shellapp.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.devin.client.shellapp.R;
import com.devin.client.shellapp.adapter.MainAdapter;
import com.devin.client.shellapp.context.ApplicationContext;
import com.devin.client.shellapp.model.Article;
import com.devin.client.shellapp.model.Image;
import com.devin.client.shellapp.model.Togathers;
import com.devin.client.shellapp.utils.VolleyInterface;
import com.devin.client.shellapp.utils.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 书凡 on 2015-11-21.
 */
public class MainFragment extends Fragment {

    private String tag;
    private  MainAdapter mainAdapter;
    @Bind(R.id.recycler)
    RecyclerView recycler;

    private Togathers togathers = new Togathers();

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public MainFragment() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(tag!=null && !tag.trim().equals(""))
            ApplicationContext.getQueues().cancelAll(tag);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
     
            requestData();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void requestData(){
        String url="http://119.29.161.112:8080/barker/cgi-bin/home";
        final List<Article> articles = new ArrayList<Article>();
        final List<Image> images = new ArrayList<Image>();
        tag = MainFragment.class.getSimpleName();
        VolleyRequest.requestGet(getActivity(), url, tag, new VolleyInterface(getActivity(), VolleyInterface.listener, VolleyInterface.errorListener) {
            @Override
            public void onMySuccess(String result) {

                JSONObject object=null;
                try {
                    object=new JSONObject(result).getJSONObject("data");
                    //图片
                    JSONObject pic = object.getJSONObject("pictures");
                    JSONArray jarrayPic= pic.getJSONArray("rows");
                    //文章
                    JSONObject art = object.getJSONObject("articles");
                    JSONArray jarrayArt= art.getJSONArray("rows");

                    for (int i=0;i<jarrayPic.length();i++){
                        JSONObject jsonObject=(JSONObject)jarrayPic.get(i);
                        Image image=new Image();
                        image.setImage(jsonObject.getString("image"));
                        images.add(image);
                    }

                    for (int i=0;i<jarrayArt.length();i++){
                        JSONObject jsonObject=(JSONObject)jarrayArt.get(i);
                        Article article=new Article();
                        article.setContext(jsonObject.getString("summary"));
                        article.setImages(jsonObject.getString("images"));
                        article.setTitle(jsonObject.getString("title"));
                        String d=jsonObject.getString("created");
                        article.setCreated(d);
                        articles.add(article);
                    }

                }catch (JSONException e){

                }
                mainAdapter = new MainAdapter(getActivity(),images,articles);
                recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                recycler.setAdapter(mainAdapter);
            }

            @Override
            public void onMyError(VolleyError error) {
                Toast.makeText(getActivity(),"网络连接超时",Toast.LENGTH_LONG).show();
            }
        });
    }
}
