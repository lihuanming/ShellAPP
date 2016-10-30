package com.devin.client.shellapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.devin.client.shellapp.R;
import com.devin.client.shellapp.context.ApplicationContext;
import com.devin.client.shellapp.model.UserBaseInfo;
import com.devin.client.shellapp.ui.fragment.UserInfoFragment;
import com.devin.client.shellapp.utils.HttpResponeCallBack;
import com.devin.client.shellapp.utils.RequestApiData;
import com.devin.client.shellapp.utils.UserPreference;
import com.devin.client.shellapp.utils.ValidateUserInfo;
import com.devin.client.shellapp.utils.constant.Constants;
import com.devin.client.shellapp.utils.constant.KeyConstance;
import com.devin.client.shellapp.utils.constant.UrlConstance;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements HttpResponeCallBack {

    @Bind(R.id.txt_cancel)
    TextView mTxtCancel;
    @Bind(R.id.txt_email)
    AutoCompleteTextView mTxtEmail;
    @Bind(R.id.txt_password)
    EditText mTxtPassword;
    @Bind(R.id.txt_forgot)
    TextView mTxtForgot;
    @Bind(R.id.txt_create)
    TextView mTxtCreate;
    @Bind(R.id.email_sign_in_button)
    Button mEmailSignInButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    /*
    * 初始化数据
    * */
    private void initView() {
        //点击“注册”
        mTxtCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //点击“忘记密码”
        mTxtForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPassActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //点击“取消”
        mTxtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //点击登录按钮
        mEmailSignInButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mTxtEmail.getText().toString();//邮箱
                String password = mTxtPassword.getText().toString();//密码

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && ValidateUserInfo.isEmailValid(email)) {
                    RequestApiData.getInstance().getLoginData(email, password, UserBaseInfo.class, LoginActivity.this);
                } else {
                    Toast.makeText(LoginActivity.this, "邮箱或密码有误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

   /* public void login() {
        Log.d(TAG, "Login");
        if (!validate()) {
            onLoginFailed();
        } else {
            mEmailSignInButton.setEnabled(false);
            Map<String, String> params = new HashMap<String, String>();
            params.put("email", mTxtEmail.getText().toString().trim());
            params.put("password", mTxtPassword.getText().toString().trim());
            //?account=yatu&password=yatu
            String url = "http://119.29.161.112:8080/barker/cgi-bin/user/find";
            VolleyRequest.requestPost(LoginActivity.this, url, tag, params,
                    new VolleyInterface(LoginActivity.this, VolleyInterface.listener,
                            VolleyInterface.errorListener) {
                        @Override
                        public void onMyError(VolleyError error) {
                            Toast.makeText(LoginActivity.this, "网络连接超时...", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onMySuccess(String result) {
                            JSONObject object = null;
                            try {
                                object = new JSONObject(result).getJSONObject("data");
                                if (object != null) {
                                    *//*String data = object.toString();
                                    String account = object.getString("email");
                                    String name = object.getString("password");*//*
                                    editor.putString("data", object.toString());
                                    //Toast.makeText(getActivity(),account,Toast.LENGTH_LONG).show();
                                    editor.putString("account", object.getString("account"));
                                    editor.putString("name", object.getString("name"));
                                    editor.commit();
                                    onLoginSuccess();
                                } else {
                                    Toast.makeText(LoginActivity.this, "邮箱或密码错误", Toast.LENGTH_LONG).show();
                                    editor.remove("account");
                                    editor.remove("name");
                                    editor.remove("data");
                                    editor.commit();
                                }
                            } catch (JSONException e) {
                                Toast.makeText(LoginActivity.this, "网络连接超时...", Toast.LENGTH_LONG).show();
                                e.printStackTrace();
                            }
                        }
                    });
        }
    }*/

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
                    UserPreference.save(KeyConstance.IS_USER_EMAIL, info.getEmail());
                    UserPreference.save(KeyConstance.IS_USER_ACCOUNT, info.getName());
                    UserPreference.save(KeyConstance.IS_USER_PASSWORD, mTxtPassword.getText().toString());

                    Intent intent = new Intent(LoginActivity.this, UserInfoFragment.class);
                    startActivity(intent);
                    //覆盖过渡
                    overridePendingTransition(android.R.anim.slide_in_left,
                            android.R.anim.slide_out_right);
                    finish();
                } else {
                    Log.e("TAG", "info=" + info.toString());
                    if (info.getErrcode().equals(Constants.KEY_NO_REGIST)) {
                        Toast.makeText(this, "此邮箱未注册！登录失败", Toast.LENGTH_SHORT).show();
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
    }
}