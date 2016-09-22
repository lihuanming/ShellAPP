package com.devin.client.shellapp.ui.fragment;

/**
 * Created by 书凡 on 2015-11-17.
 */

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
import com.devin.client.shellapp.adapter.TogatherAdatper;
import com.devin.client.shellapp.context.ApplicationContext;
import com.devin.client.shellapp.model.Store;
import com.devin.client.shellapp.model.Togathers;
import com.devin.client.shellapp.utils.VolleyInterface;
import com.devin.client.shellapp.utils.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class TogatherFragment extends Fragment {
    private String tag;
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    @Bind(R.id.recycler)
    RecyclerView recycler;
    @Override
    public void onStop() {
        super.onStop();
        if(tag!=null && !tag.trim().equals(""))
            ApplicationContext.getQueues().cancelAll(tag);
    }

    private Togathers togathers = new Togathers();
    private TogatherAdatper togatherAdatper;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TogatherFragment newInstance() {
        TogatherFragment fragment = new TogatherFragment();
        return fragment;
    }

    private void requestData(){
        String url="http://119.29.161.112:8080/barker/cgi-bin/store/list";
        final List<Store> stores = new ArrayList<Store>();
        tag = MainFragment.class.getSimpleName();
        VolleyRequest.requestGet(getActivity(), url, tag, new VolleyInterface(getActivity(), VolleyInterface.listener, VolleyInterface.errorListener) {
            @Override
            public void onMySuccess(String result) {
                /*Toast.makeText(getActivity(),result.toString(),Toast.LENGTH_LONG).show();*/
                JSONObject object=null;
                try {
                    object=new JSONObject(result).getJSONObject("data");
                    JSONArray datas= object.getJSONArray("rows");

                    for (int i=0;i<datas.length();i++){
                        JSONObject jsonObject=(JSONObject)datas.get(i);
                        Store store=new Store();
                        store.setTitle(jsonObject.getString("title"));
                        store.setAddress(jsonObject.getString("address"));
                        store.setCollect(jsonObject.getInt("collect"));
                        store.setNumber(jsonObject.getInt("number"));
                        store.setImage(jsonObject.getString("image"));
                        store.setComment(jsonObject.getInt("comment"));
                        store.setInterest(jsonObject.getInt("interest"));
                        store.setTel(jsonObject.getString("tel"));
                        stores.add(store);
                    }

                }catch (JSONException e){

                }
                togatherAdatper = new TogatherAdatper(getActivity(),stores);
                recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                recycler.setAdapter(togatherAdatper);
            }

            @Override
            public void onMyError(VolleyError error) {
                Toast.makeText(getActivity(),"网络连接超时",Toast.LENGTH_LONG).show();
            }
        });
    }

    public TogatherFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_togather, container, false);
        ButterKnife.bind(this, rootView);

       
            requestData();
        

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}