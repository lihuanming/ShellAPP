package com.devin.client.shellapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.devin.client.shellapp.R;
import com.devin.client.shellapp.model.Status;
import com.devin.client.shellapp.utils.CleanEditText;
import com.devin.client.shellapp.utils.NetworkUtils;
import com.devin.client.shellapp.utils.UserPreference;
import com.devin.client.shellapp.utils.ValidateUserInfo;
import com.devin.client.shellapp.utils.constant.KeyConstance;
import com.devin.client.shellapp.utils.constant.UrlConstance;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.iv_cancel)
    ImageView mIvCancel;
    @Bind(R.id.et_phone)
    CleanEditText mEtPhone;
    @Bind(R.id.et_password)
    CleanEditText mEtPassword;
    @Bind(R.id.btn_login)
    Button mBtnLogin;
    @Bind(R.id.tv_create_account)
    TextView mTvCreateAccount;
    @Bind(R.id.tv_forget_password)
    TextView mTvForgetPassword;
    private RequestQueue requestQueue;
    private static final String TAG = "MyTag";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
        if (!NetworkUtils.isNetworkAvailable()) {
            Toast.makeText(this, "网络不可用...", Toast.LENGTH_SHORT).show();
        }

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
    public class Text{
        public String phone;
        public String password;
    }
    /*
    * 初始化数据
    * */
    private void initView() {
        //点击“注册”
        mTvCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //点击“忘记密码”
        mTvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPassActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //点击“取消”
        mIvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //点击登录按钮
        mBtnLogin.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
               // clickLogin();
                //获取用户注册输入的信息
                final String phoneEdit = mEtPhone.getText().toString();
                final String passwordEdit = mEtPassword.getText().toString();
                if (TextUtils.isEmpty(phoneEdit)){
                    Toast.makeText(LoginActivity.this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
                }else if (!ValidateUserInfo.isMobileValid(phoneEdit)) {
                        Toast.makeText(LoginActivity.this, "手机号码格式不正确", Toast.LENGTH_SHORT).show();
                    }else if (!ValidateUserInfo.isPasswordValid(passwordEdit)){
                        Toast.makeText(LoginActivity.this, "请输入6-16个密码", Toast.LENGTH_SHORT).show();
                    }else {
                        requestQueue = Volley.newRequestQueue(LoginActivity.this);
                        String url = UrlConstance.KEY_LOGIN_INFO;
                        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Gson gson = new Gson();
                                Status s = gson.fromJson(response,Status.class);
                                Log.i("TAGACCPTE",response);
                                if (s.code == 400){
                                    Toast.makeText(LoginActivity.this, "手机号或密码错误,请重试找回密码", Toast.LENGTH_SHORT).show();
                                }else {
                                    responseData(phoneEdit.trim(),passwordEdit.trim(),s.getResult().getToken());
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.i("TAGError", error.toString());
                                if (error.toString().contains("400")){
                                    Toast.makeText(LoginActivity.this, "手机号或密码错误,请重试找回密码", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }) {
                            @Override
                            public Map<String, String> getHeaders() throws AuthFailureError {
                                HashMap<String, String> header = new HashMap<>();
                                header.put("Content-Type", "application/json");
                                header.put("Accept", "application/json");
                                return header;
                            }

                            @Override
                            public byte[] getBody() throws AuthFailureError {
                                LoginActivity.Text text = new LoginActivity.Text();
                                text.password = passwordEdit;
                                text.phone = phoneEdit;
                                Gson gson = new Gson();
                                String str = gson.toJson(text);
                                Log.i("TAG",text.password + " " + text.phone + " ");
                                return str.getBytes();
                            }
                        };
                        requestQueue.add(stringRequest);
                    }

            }

        });
    }

    private void responseData(String phone,String password,String token){
        UserPreference.save(KeyConstance.IS_USER_PHONE,phone);
        UserPreference.save(KeyConstance.IS_USER_PASSWORD,password);
        UserPreference.save(UrlConstance.ACCESSTOKEN_KEY,token);
        Toast.makeText(LoginActivity.this, "登陆成功" , Toast.LENGTH_SHORT).show();
        finish();
    }

/*
    private void clickLogin() {
        String phoneEdit = mEtPhone.getText().toString();//手机号码
        String passwordEdit = mEtPassword.getText().toString();//密码

        if (!checkInput(phoneEdit, passwordEdit)) {
            //输入信息不匹配，登陆失败
            Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show();
        } else {
            //请求服务器获取数据登录账号
            RequestApiData.getInstance().getLoginData(phoneEdit, passwordEdit, UserBaseInfo.class, LoginActivity.this);
            finish();
        }
    }

    *//**
     * 检查输入
     *
     * @param phoneEdit
     * @param passwordEdit
     * @return
     *//*
    private boolean checkInput(String phoneEdit, String passwordEdit) {
        //账号为空时提示
        if (phoneEdit == null || phoneEdit.trim().equals("")) {
            Toast.makeText(this, R.string.tip_account_empty, Toast.LENGTH_LONG).show();
        } else {
            //账号不匹配手机号码格式
            if (!ValidateUserInfo.isMobileValid(phoneEdit)) {
                 Toast.makeText(this, R.string.tip_account_regex_not_right, Toast.LENGTH_LONG).show();
            } else if (passwordEdit == null || passwordEdit.trim().equals("")) {
                Toast.makeText(this, R.string.tip_password_can_not_be_empty, Toast.LENGTH_SHORT).show();
            } else {
                return true;
            }
        }

        return false;
    }

    @Override
    public void onResponeStart(String apiName) {
        if (UrlConstance.KEY_LOGIN_INFO.equals(apiName)) {
            Toast.makeText(this, "正在加载数据中...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoading(String apiName, long count, long current) {
        Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(String apiName, Object object) {
        if (UrlConstance.KEY_LOGIN_INFO.equals(apiName)) {
            //邮箱登录返回数据
            if (object != null && object instanceof UserBaseInfo) {
                UserBaseInfo info = (UserBaseInfo) object;
                if (info.getRet().equals(Constants.KEY_SUCCESS)) {
                    //登录成功，保存登录信息
                    ApplicationContext.getInstance().setBaseUser(info);//保存到Application中
                    //保存到SP中
                    UserPreference.save(KeyConstance.IS_USER_ID, info.getUserid());
                    UserPreference.save(KeyConstance.IS_USER_PHONE, info.getPhone());
                    UserPreference.save(KeyConstance.IS_USER_ACCOUNT, info.getNickname());
                    UserPreference.save(KeyConstance.IS_USER_PASSWORD, mEtPassword.getText().toString());

                  *//*  Intent intent = new Intent(LoginActivity.this, UserInfoFragment.class);
                    startActivity(intent);*//*
                    //覆盖过渡
                    overridePendingTransition(android.R.anim.slide_in_left,
                            android.R.anim.slide_out_right);
                    finish();
                } else {
                    Log.e("TAG", "info=" + info.toString());
                    if (info.getErrcode().equals(Constants.KEY_NO_REGIST)) {
                        Toast.makeText(this, "此手机号码未注册！登录失败", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, info.getMsg(), Toast.LENGTH_SHORT).show();
                        Log.e("TAG", "info.getMsg()=" + info.getMsg());
                    }
                }
            }
        }
    }

    @Override
    public void onFailure(String apiName, Throwable throwable, int errorNo, String strMsg) {
        Toast.makeText(this, "Failure...", Toast.LENGTH_SHORT).show();
    }*/
}