package com.devin.client.shellapp.ui.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.devin.client.shellapp.R;
import com.devin.client.shellapp.ui.activity.MainTabActivity;
import com.devin.client.shellapp.utils.VolleyInterface;
import com.devin.client.shellapp.utils.VolleyRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link nologinFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class nologinFragment extends Fragment {
    private SharedPreferences sharedPreferences;// 定义SharedPreferences，用于保存密码
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private SharedPreferences.Editor  editor;
    private String tag ;
    // TODO: Rename and change types of parameters
  /*  private String mParam1;
    private String mParam2;
*/

    public nologinFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment nologinFragment.
     */
    // TODO: Rename and change types and number of parameters
  /*  public static nologinFragment newInstance(String param1, String param2) {
        nologinFragment fragment = new nologinFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           /* mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);*/
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        
        View view = null;
        sharedPreferences = getActivity()
                .getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String account = sharedPreferences.getString("account", null);
        // 读取密码信息，空取默认""
        String name = sharedPreferences.getString("name", null);
        
        // 判断账号密码是否空
        if (account != null && name != null) {
            view =  inflater.inflate(R.layout.fragment_user_info,container, false);
            initUserInfoView(view);
        } else{
            view = inflater.inflate(R.layout.fragment_login,container, false);
            initLoginView(view);
        }
        return view;
    }

    public static nologinFragment newInstance() {
        nologinFragment fragment = new nologinFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onStop() {
        super.onStop();
    }

    private void initLoginView(View view){
        tag = nologinFragment.class.getSimpleName();
        final EditText account=(EditText)view.findViewById(R.id.txt_email);
        final EditText password=(EditText)view.findViewById(R.id.txt_password);
        Button loginbtn=(Button)view.findViewById(R.id.email_sign_in_button);
        
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(account.getText().toString().trim().equals("")){
                    account.requestFocus();
                    Toast.makeText(getContext(),"邮箱/手机号码不许为空",Toast.LENGTH_LONG).show();
                }else if(password.getText().toString().trim().equals("")){
                    password.requestFocus();
                    Toast.makeText(getContext(),"密码不许为空",Toast.LENGTH_LONG).show();
                }else{
                    Map<String,String> params=new HashMap<String, String>();
                    params.put("account",account.getText().toString().trim());
                    params.put("password",password.getText().toString().trim());
                    //?account=yatu&password=yatu
                    String url = "http://119.29.161.112:8080/barker/cgi-bin/user/find";
                    VolleyRequest.requestPost(getContext(),url,tag,params, new VolleyInterface(getActivity(), VolleyInterface.listener, VolleyInterface.errorListener){
                        @Override
                        public void onMyError(VolleyError error) {
                            Toast.makeText(getContext(),"网络连接超时...",Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onMySuccess(String result) {
                            JSONObject object=null;
                            try{
                                object=new JSONObject(result).getJSONObject("data");
                                if(object!=null){
                                    String data= object.toString();
                                    String account= object.getString("account");
                                    String name = object.getString("name");
                                    editor.putString("data",object.toString());
                                    //Toast.makeText(getActivity(),account,Toast.LENGTH_LONG).show();
                                    editor.putString("account",object.getString("account"));
                                    editor.putString("name",object.getString("name"));
                                    editor.commit();
                                    MainTabActivity.tabMain.setCurrentTab(0);
                                    MainTabActivity.line.setVisibility(View.VISIBLE);
                                    MainTabActivity.commonTabLayout.setVisibility(View.VISIBLE);
                                }else{
                                    Toast.makeText(getContext(),"邮箱/手机号码,或密码错误",Toast.LENGTH_LONG).show();
                                    editor.remove("account");
                                    editor.remove("name");
                                    editor.remove("data");
                                    editor.commit();
                                }
                            }catch (JSONException e){
                                Toast.makeText(getContext(),"网络连接超时...",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
               
            }
        });
    }
    
    private void initUserInfoView(View view){
        //取数据json
        String data = sharedPreferences.getString("data",null);
        JSONObject object = null;
        try{
            object=new JSONObject(data);
            //{"account":"yatu","name":"yatu","headimage":"1","code":"","address":"广州从化","zone":"广州从化","sex":1,"sign":"","password":null,"email":null}
            object.getString("account");
        }catch (JSONException e){
            
        }
        
//        view.findViewById()
    }
}
