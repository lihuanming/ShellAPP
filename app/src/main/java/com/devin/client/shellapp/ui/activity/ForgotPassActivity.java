package com.devin.client.shellapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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


public class ForgotPassActivity extends AppCompatActivity {

    @Bind(R.id.et_phone)
    CleanEditText mEtPhone;
    @Bind(R.id.btn_send_verifi_code)
    Button mBtnSendVerifiCode;
    @Bind(R.id.et_verifiCode)
    CleanEditText mEtVerifiCode;
    @Bind(R.id.et_password)
    CleanEditText mEtPassword;
    @Bind(R.id.btn_create_account)
    Button mBtnCreateAccount;
    @Bind(R.id.txt_remembered)
    TextView mTxtRemembered;

    private VerifyCodeManager codeManager;
    RequestQueue requestQueue;
    private static final String TAG = "MyTag";
    private Status s;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        ButterKnife.bind(this);
        initViews();
        if (!NetworkUtils.isNetworkAvailable()) {
            Toast.makeText(this, "网络不可用...", Toast.LENGTH_SHORT).show();
        }
        codeManager = new VerifyCodeManager(this, mEtPhone, mBtnSendVerifiCode);
    }

    public class Text {
        public String phone;
        public String password;


    }

    private void initViews() {

        //重置密码按钮
        mBtnCreateAccount.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //commitInfo();
                //获取用户注册输入的信息
                final String phoneEdit = mEtPhone.getText().toString();
                final String passwordEdit = mEtPassword.getText().toString();
                String verifyCodeEdit = mEtVerifiCode.getText().toString();
                if (TextUtils.isEmpty(phoneEdit) || !ValidateUserInfo.isMobileValid(phoneEdit)) {
                    Toast.makeText(ForgotPassActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(verifyCodeEdit)) {
                    Toast.makeText(ForgotPassActivity.this, "验证码不能为空,请输入", Toast.LENGTH_SHORT).show();
                } else if (!ValidateUserInfo.isPasswordValid(passwordEdit)) {
                    Toast.makeText(ForgotPassActivity.this, "请输入6-16个密码", Toast.LENGTH_SHORT).show();
                } else {
                    if (s.code == 40002) {
                        Toast.makeText(ForgotPassActivity.this, "新旧密码不能相同", Toast.LENGTH_SHORT).show();
                    } else {
                        requestQueue = Volley.newRequestQueue(ForgotPassActivity.this);
                        String url = UrlConstance.KEY_RET_PASSWORD;
                        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.i("TAG", response);
                                Gson gson = new Gson();
                                s = gson.fromJson(response, Status.class);
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
                                header.put("Content-Type", "application/json");
                                header.put("Accept", "application/json");
                                return header;
                            }

                            @Override
                            public byte[] getBody() throws AuthFailureError {

                                ForgotPassActivity.Text text = new ForgotPassActivity.Text();
                                text.password = passwordEdit;
                                text.phone = phoneEdit;
                                Gson gson = new Gson();
                                String str = gson.toJson(text);
                                Log.i("TAG", text.password + " " + text.phone + " ");
                                return str.getBytes();
                            }
                        };
                        requestQueue.add(stringRequest);
                        Toast.makeText(ForgotPassActivity.this, "重置密码成功，请登陆", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ForgotPassActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }


            }

        });
        //返回登录页面
        mTxtRemembered.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPassActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //获取验证码
        mBtnSendVerifiCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 请求接口发送验证码
                if (!ValidateUserInfo.isMobileValid(mEtPhone.getText().toString())) {
                    Toast.makeText(ForgotPassActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                } else {
                    codeManager.getVerifyCode(VerifyCodeManager.RESET_PWD);
                    requestQueue = Volley.newRequestQueue(ForgotPassActivity.this);
                    String url = UrlConstance.KEY_PHONE_VERIFYKYCODE;
                    final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.i("TAG", response);
                            Gson gson = new Gson();
                            s = gson.fromJson(response, Status.class);
                            if (s.code == 400) {
                                Toast.makeText(ForgotPassActivity.this, "服务器异常，请稍后再获取验证码", Toast.LENGTH_SHORT).show();
                            }
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
                            header.put("Content-Type", "application/json");
                            header.put("Accept", "application/json");
                            return header;
                        }

                        @Override
                        public byte[] getBody() throws AuthFailureError {
                            String body = "{\"phone\":\"" + mEtPhone.getText().toString() + "\"}";
                            return body.getBytes();
                        }
                    };
                    requestQueue.add(stringRequest);
                    Toast.makeText(ForgotPassActivity.this, "短信已发送，请注意查收", Toast.LENGTH_SHORT).show();
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

  /*  private void commitInfo() {
        //获取用户输入的信息
        String phoneEdit = mEtPhone.getText().toString();
        String passwordEdit = mEtPassword.getText().toString();
        String verifyCodeEdit = mEtVerifiCode.getText().toString();
        if (checkInput(phoneEdit, passwordEdit, verifyCodeEdit)) {
            Toast.makeText(ForgotPassActivity.this, "重置密码失败", Toast.LENGTH_SHORT).show();
        } else {
            //SMSSDK.submitVerificationCode("86", phoneEdit.trim(), verifyCodeEdit.trim());
            //请求服务端
            RequestApiData.getInstance().getResetPasswordData( phoneEdit, passwordEdit, verifyCodeEdit,
                    AnalyticalRegistInfo.class, ForgotPassActivity.this);
            Toast.makeText(this, "重置密码成功，请登录", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(ForgotPassActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private boolean checkInput(String phoneEdit, String passwordEdit, String verifyCodeEdit) {
        if (TextUtils.isEmpty(phoneEdit)) {
            Toast.makeText(this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            if (!ValidateUserInfo.isMobileValid(phoneEdit)) {
                Toast.makeText(this, "手机号码格式不正确", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(verifyCodeEdit)) {
                Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            } else if (!ValidateUserInfo.isPasswordValid(passwordEdit)) {
                Toast.makeText(this, "请输入6-16个密码", Toast.LENGTH_SHORT).show();
            } else {
                return true;
            }
        }
        return false;
    }


    @Override
    public void onResponeStart(String apiName) {
        // Toast.makeText(this, "正在请求数据...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoading(String apiName, long count, long current) {
        // Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(String apiName, Object object) {

    }

    @Override
    public void onFailure(String apiName, Throwable throwable, int errorNo, String strMsg) {
        // Toast.makeText(this, "Failure...", Toast.LENGTH_SHORT).show();
    }*/

}
