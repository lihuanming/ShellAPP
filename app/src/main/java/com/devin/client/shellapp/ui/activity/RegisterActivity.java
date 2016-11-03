package com.devin.client.shellapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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
import com.devin.client.shellapp.utils.ValidateUserInfo;
import com.devin.client.shellapp.utils.VerifyCodeManager;
import com.devin.client.shellapp.utils.constant.UrlConstance;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;


public class RegisterActivity extends AppCompatActivity {


    @Bind(R.id.btn_register)
    Button mBtnRegister;                //注册按钮
    @Bind(R.id.txt_already_have)
    TextView mTxtAlreadyHave;           //"已经有账号"
    @Bind(R.id.et_phone)
    CleanEditText mEtPhone;             //手机号码
    @Bind(R.id.btn_send_verifi_code)
    Button mBtnSendVerifiCode;          //发送验证码按钮
    @Bind(R.id.et_password)
    CleanEditText mEtPassword;          //密码
    @Bind(R.id.et_repassword)
    CleanEditText mEtRepassword;        //确认密码
    @Bind(R.id.et_verifiCode)
    CleanEditText mEtVerifiCode;        //验证码
    @Bind(R.id.et_nickname)
    CleanEditText mEtNickname;          //用户名
    private VerifyCodeManager codeManager;
    private RequestQueue requestQueue;
    private static final String TAG = "MyTag";
    private Status s;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);
        initView();

        codeManager = new VerifyCodeManager(this, mEtPhone, mBtnSendVerifiCode);
        if (!NetworkUtils.isNetworkAvailable()) {
            Toast.makeText(this, "网络不可用...", Toast.LENGTH_SHORT).show();
        }
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void initView() {
        //注册按钮
        mBtnRegister.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //commitInfo();
                //获取用户注册输入的信息
                final String nicknameEdit = mEtNickname.getText().toString();
                final String phoneEdit = mEtPhone.getText().toString();
                final String passwordEdit = mEtPassword.getText().toString();
                String repasswordEdit = mEtRepassword.getText().toString();
                final String verifyCodeEdit = mEtVerifiCode.getText().toString();
               /* requestQueue = Volley.newRequestQueue(RegisterActivity.this);
                String url = UrlConstance.KEY_REGIST_INFO;
                final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("TAG", response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("TAG", error.toString());
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> header = new HashMap<>();
                        header.put("Content-Type", "application/json;charset=utf-8");
                        header.put("Accept", "application/json");
                        return header;
                    }

                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        Text text = new Text();
                        text.password = passwordEdit;
                        text.phone = phoneEdit;
                        Gson gson = new Gson();
                        String str = gson.toJson(text);
                        Log.i("TAG", text.password + " " + text.phone + " ");
                        return str.getBytes();
                    }
                };
                requestQueue.add(stringRequest);
                Toast.makeText(RegisterActivity.this, "注册成功，请登陆", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();*/
                if (TextUtils.isEmpty(phoneEdit) || !ValidateUserInfo.isMobileValid(phoneEdit)) {
                    Toast.makeText(RegisterActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(verifyCodeEdit)) {
                    Toast.makeText(RegisterActivity.this, "验证码不能为空,请重新输入", Toast.LENGTH_SHORT).show();
                } else if (!ValidateUserInfo.isPasswordValid(passwordEdit)) {
                    Toast.makeText(RegisterActivity.this, "请输入6-16个密码", Toast.LENGTH_SHORT).show();
                } else if (!passwordEdit.trim().equals(repasswordEdit.trim())) {
                    Toast.makeText(RegisterActivity.this, "密码不一致", Toast.LENGTH_SHORT).show();
                } else if (!ValidateUserInfo.isNameValid(nicknameEdit)) {
                    Toast.makeText(RegisterActivity.this, "用户名须为5-17个字母数字组成", Toast.LENGTH_SHORT).show();
                } else {
                    /*if (!verifyCodeEdit.equals(s.getResult().toString())) {

                        Toast.makeText(RegisterActivity.this, "验证码错误,重新输入", Toast.LENGTH_SHORT).show();
                        mEtVerifiCode.setText("");
                        mEtVerifiCode.setFocusableInTouchMode(true);
                    } else {*/
                        //Log.i("TAG",s2.result.toString());
                        requestQueue = Volley.newRequestQueue(RegisterActivity.this);
                        String url = UrlConstance.KEY_REGIST_INFO;
                        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.i("TAG", response);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.i("TAG", error.toString());
                            }
                        }) {
                            @Override
                            public Map<String, String> getHeaders() throws AuthFailureError {
                                HashMap<String, String> header = new HashMap<>();
                                header.put("Content-Type", "application/json;charset=utf-8");
                                header.put("Accept", "application/json");
                                return header;
                            }

                            @Override
                            public byte[] getBody() throws AuthFailureError {
                                Text text = new Text();
                                text.password = passwordEdit;
                                text.phone = phoneEdit;
                                Gson gson = new Gson();
                                String str = gson.toJson(text);
                                Log.i("TAG", text.password + " " + text.phone + " ");
                                return str.getBytes();
                            }
                        };
                        requestQueue.add(stringRequest);
                        Toast.makeText(RegisterActivity.this, "注册成功，请登陆", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                /*}*/
            }
        });
        //返回登录页面
        mTxtAlreadyHave.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //获取验证码
        mBtnSendVerifiCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ValidateUserInfo.isMobileValid(mEtPhone.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                } else {
                    codeManager.getVerifyCode(VerifyCodeManager.REGISTER);
                    requestQueue = Volley.newRequestQueue(RegisterActivity.this);
                    String url = UrlConstance.KEY_PHONE_VERIFYKYCODE;
                    final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("TAG", response);
                            Gson gson = new Gson();
                            s = gson.fromJson(response, Status.class);
                            //Log.i("TAGCode", s1.result.toString());

                            if (s.code == 400) {
                                Toast.makeText(RegisterActivity.this, "服务器异常，请稍后再获取验证码", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("TAG", error.toString());
                            Toast.makeText(RegisterActivity.this, "服务器异常，请稍后再获取验证码", Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> header = new HashMap<>();
                            header.put("Content-Type", "application/json;charset=utf-8");
                            header.put("Accept", "application/json");
                            return header;
                        }

                        @Override
                        public byte[] getBody() throws AuthFailureError {
                            Text text = new Text();
                            text.phone = mEtPhone.getText().toString();
                            Gson gson = new Gson();
                            String string = gson.toJson(text);
                            Log.i("TAG", text.phone);
                            //String body = "{\"phone\":\"" + mEtPhone.getText().toString() + "\"}";
                            return string.getBytes();
                        }
                    };
                    requestQueue.add(stringRequest);
                    Toast.makeText(RegisterActivity.this, "短信已发送，请注意查收", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (requestQueue != null) {
            requestQueue.cancelAll(TAG);
        }
    }
//提交注册信息
   /* private void commitInfo() {
        //获取用户注册输入的信息
        String nicknameEdit = mEtNickname.getText().toString();
        String phoneEdit = mEtPhone.getText().toString();
        String passwordEdit = mEtPassword.getText().toString();
        String repasswordEdit = mEtRepassword.getText().toString();
        String verifyCodeEdit = mEtVerifiCode.getText().toString();
        if (TextUtils.isEmpty(phoneEdit)) {
            Toast.makeText(this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            if (!ValidateUserInfo.isMobileValid(phoneEdit)) {
                Toast.makeText(this, "手机号码格式不正确", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(verifyCodeEdit)) {
                Toast.makeText(this, "验证码不正确", Toast.LENGTH_SHORT).show();
            } else if (!ValidateUserInfo.isPasswordValid(passwordEdit)) {
                Toast.makeText(this, "请输入6-16个密码", Toast.LENGTH_SHORT).show();
            } else if (!passwordEdit.equals(repasswordEdit)) {//两次输入的密码不一致
                Toast.makeText(this, R.string.tip_Entered_passwords_differ, Toast.LENGTH_SHORT).show();
            } else if (!ValidateUserInfo.isPasswordValid(nicknameEdit)) {
                Toast.makeText(this, "用户名须由5-17个字符（字母/数字）组成", Toast.LENGTH_SHORT).show();
            } else {
                //请求服务端注册账号
                RequestApiData.getInstance().getRegisterData( phoneEdit, passwordEdit,
                        AnalyticalRegistInfo.class, RegisterActivity.this);
                finish();
            }
        }
        *//*if (!checkInput(nicknameEdit, phoneEdit, passwordEdit, repasswordEdit, verifyCodeEdit)) {
            Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
        } else {

            //请求服务端注册账号
            RequestApiData.getInstance().getRegisterData(nicknameEdit, phoneEdit, passwordEdit, verifyCodeEdit,
                    AnalyticalRegistInfo.class, RegisterActivity.this);
            Toast.makeText(this, "注册成功，正在返回...", Toast.LENGTH_SHORT).show();
            finish();

        }*//*
    }

*//*    private boolean checkInput(String nicknameEdit, String phoneEdit, String passwordEdit,
                               String repasswordEdit, String verifyCodeEdit) {
        if (TextUtils.isEmpty(phoneEdit)) {
            Toast.makeText(this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            if (!ValidateUserInfo.isMobileValid(phoneEdit)) {
                Toast.makeText(this, "手机号码格式不正确", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(verifyCodeEdit)) {
                Toast.makeText(this, "验证码不正确", Toast.LENGTH_SHORT).show();
            } else if (!ValidateUserInfo.isPasswordValid(passwordEdit)) {
                Toast.makeText(this, "请输入6-16个密码", Toast.LENGTH_SHORT).show();
            } else if (!passwordEdit.equals(repasswordEdit)) {//两次输入的密码不一致
                Toast.makeText(this, R.string.tip_Entered_passwords_differ, Toast.LENGTH_SHORT).show();
            } else if (!ValidateUserInfo.isPasswordValid(nicknameEdit)) {
                Toast.makeText(this, "用户名须由5-17个字符（字母/数字）组成", Toast.LENGTH_SHORT).show();
            } else {
                return true;
            }
        }
        return false;
    }*//*

    @Override
    public void onResponeStart(String apiName) {
        //Toast.makeText(this, "正在请求数据...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoading(String apiName, long count, long current) {
        //Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(String apiName, Object object) {
        //注册接口

        if (UrlConstance.KEY_REGIST_INFO.equals(apiName)) {

            if (object != null && object instanceof AnalyticalRegistInfo) {
                AnalyticalRegistInfo info = (AnalyticalRegistInfo) object;
                String successCode = info.getRet();
                //请求成功

                if (successCode.equals(Constants.KEY_SUCCESS)) {
                    UserBaseInfo baseUser = new UserBaseInfo();
                    baseUser.setPhone(info.getPhone());//手机号码
                    baseUser.setNickname(info.getNickname());//用户名
                    //baseUser.setUserhead(info.getUserhead());//用户头像
                    baseUser.setUserid(String.valueOf(info.getUserid()));//用户ID

                    ApplicationContext.getInstance().setBaseUser(baseUser);
                    UserPreference.save(KeyConstance.IS_USER_ID, String.valueOf(info.getUserid()));
                    UserPreference.save(KeyConstance.IS_USER_ACCOUNT, info.getNickname());
                    UserPreference.save(KeyConstance.IS_USER_PHONE, info.getPhone());
                    UserPreference.save(KeyConstance.IS_USER_PASSWORD, mEtPassword.getText().toString());

                   *//* Intent intent = new Intent(RegisterActivity.this, UserInfoFragment.class);
                    RegisterActivity.this.startActivity(intent);*//*

                    Toast.makeText(this, "恭喜你！注册成功...", Toast.LENGTH_SHORT).show();
                    RegisterActivity.this.finish();
                } else {
                    Toast.makeText(this, "很遗憾！注册失败...", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onFailure(String apiName, Throwable throwable, int errorNo, String strMsg) {
        //Toast.makeText(this, "Failure...", Toast.LENGTH_SHORT).show();
    }*/


    public class Text {
        public String phone;
        public String password;


    }
}