package com.devin.client.shellapp.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.devin.client.shellapp.R;
import com.devin.client.shellapp.utils.VolleyInterface;
import com.devin.client.shellapp.utils.VolleyRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

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

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private static final int REQUEST_CODE_TO_REGISTER = 0x001;
    private SharedPreferences sharedPreferences;
    private String tag;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        // 数据的回显
        // 从sharedPreferences中获取数据
        String email = sharedPreferences.getString("email", "");
        String password = sharedPreferences.getString("password", "");
        /*if (email.isEmpty()){
            mTxtEmail.setError("邮箱不能为空！");
        }else if(pwd.isEmpty()){
            mTxtPassword.setError("密码不能为空！");
        }else {
            // 给AutoCompleteTextView设置数据
            mTxtEmail.setText(email);
            mTxtPassword.setText(pwd);
        }*/
    }

    //页面控件操作
    @OnClick({R.id.txt_cancel, R.id.txt_forgot, R.id.txt_create, R.id.email_sign_in_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_cancel:
                backUserInfo();
                break;
            case R.id.txt_forgot:   //忘记密码
                enterForgetPwd();
                break;
            case R.id.txt_create:   //注册
                enterRegister();
                break;
            case R.id.email_sign_in_button: //登陆
                login();
                break;
            default:
                break;
        }
    }

    /**
     * 跳转到个人信息页面
     */
    public void backUserInfo() {
        /*Intent intent = new Intent(this, UserInfoFragment.class);
        startActivity(intent);*/
        finish();
    }

    /**
     * 跳转到忘记密码
     */
    private void enterForgetPwd() {
        Intent intent = new Intent(this, ForgotPassActivity.class);
        startActivity(intent);
    }

    /**
     * 跳转到注册页面
     */
    private void enterRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivityForResult(intent, REQUEST_CODE_TO_REGISTER);
    }

    //登陆校验
    public void login() {
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
            VolleyRequest.requestPost(LoginActivity.this, url, tag, params, new VolleyInterface(LoginActivity.this, VolleyInterface.listener, VolleyInterface.errorListener) {
                @Override
                public void onMyError(VolleyError error) {
                    Toast.makeText(LoginActivity.this, "网络连接超时...", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onMySuccess(String result) {
                    JSONObject object=null;
                    try {
                        object = new JSONObject(result).getJSONObject("data");
                        if (object != null) {
                            String data = object.toString();
                            String account = object.getString("email");
                            String name = object.getString("password");
                            editor.putString("data", object.toString());
                            //Toast.makeText(getActivity(),account,Toast.LENGTH_LONG).show();
                            editor.putString("account", object.getString("account"));
                            editor.putString("name", object.getString("name"));
                            editor.commit();
                            onLoginSuccess();
                          /*  MainTabActivity.tabMain.setCurrentTab(0);
                            MainTabActivity.line.setVisibility(View.VISIBLE);
                            MainTabActivity.commonTabLayout.setVisibility(View.VISIBLE);*/
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
    }

    /*
    * 实现成功的注册逻辑
    */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (requestCode == RESULT_OK) {
                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        // 禁止按返回键
        moveTaskToBack(true);
    }

    //登陆成功
    public void onLoginSuccess() {
        mEmailSignInButton.setEnabled(true);
        finish();
    }

    //登陆失败
    public void onLoginFailed() {
       /* Toast.makeText(getBaseContext(), "登录失败！", Toast.LENGTH_LONG).show();*/
        mEmailSignInButton.setEnabled(true);
    }

    //邮箱、密码校验
    public boolean validate() {
        boolean valid = true;

        String email = mTxtEmail.getText().toString();
        String password = mTxtPassword.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mTxtEmail.setError("请输入正确的邮箱！");
            mTxtEmail.requestFocus();
            valid = false;
        } else {
            mTxtEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 ) {
            mTxtPassword.setError("密码的长度必须大于4！");
            mTxtPassword.requestFocus();
            valid = false;
        } else {
            mTxtPassword.setError(null);
        }
        return valid;
    }

}