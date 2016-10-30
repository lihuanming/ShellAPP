package com.devin.client.shellapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.devin.client.shellapp.R;
import com.devin.client.shellapp.context.ApplicationContext;
import com.devin.client.shellapp.model.AnalyticalRegistInfo;
import com.devin.client.shellapp.model.UserBaseInfo;
import com.devin.client.shellapp.utils.HttpResponeCallBack;
import com.devin.client.shellapp.utils.NetworkUtils;
import com.devin.client.shellapp.utils.RequestApiData;
import com.devin.client.shellapp.utils.UserPreference;
import com.devin.client.shellapp.utils.ValidateUserInfo;
import com.devin.client.shellapp.utils.constant.Constants;
import com.devin.client.shellapp.utils.constant.KeyConstance;
import com.devin.client.shellapp.utils.constant.UrlConstance;

import butterknife.Bind;
import butterknife.ButterKnife;


public class RegisterActivity extends AppCompatActivity implements HttpResponeCallBack {


    @Bind(R.id.edit_name)
    AutoCompleteTextView mEditName;     //用户昵称
    @Bind(R.id.edit_email)
    AutoCompleteTextView mEditEmail;    //注册邮箱
    @Bind(R.id.edit_password)
    EditText mEditPassword;              //注册密码
    @Bind(R.id.btn_register)
    Button mBtnRegister;                //注册按钮
    @Bind(R.id.txt_already_have)
    TextView mTxtAlreadyHave;           //"已经有账号"
    @Bind(R.id.edit_repassword)
    EditText mEditRepassword;           //确认密码

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);
        initView();
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
                //获取用户输入的信息
                final String name = mEditName.getText().toString();
                final String email = mEditEmail.getText().toString();
                String password = mEditPassword.getText().toString();
                String repassword = mEditRepassword.getText().toString();
                if (!TextUtils.isEmpty(name) &&
                        !TextUtils.isEmpty(email)
                        && !TextUtils.isEmpty(password)
                        && TextUtils.isEmpty(repassword)) {
                    if (ValidateUserInfo.isNameValid(name)//验证昵称、密码、邮箱格式是否符合
                            && ValidateUserInfo.isPasswordValid(password)
                            && ValidateUserInfo.isEmailValid(email)
                            && ValidateUserInfo.isPasswordValid(repassword)) {
                        if (password.equals(repassword)) {
                            RequestApiData.getInstance().getRegisterData(name, email, password,
                                    AnalyticalRegistInfo.class, RegisterActivity.this);
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "输入信息有误", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "输入信息未完全", Toast.LENGTH_SHORT).show();
                }
            }

        });
        mTxtAlreadyHave.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onResponeStart(String apiName) {
        Toast.makeText(this, "正在请求数据...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoading(String apiName, long count, long current) {
        Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show();
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
                    baseUser.setName(info.getName());//昵称
                    baseUser.setEmail(info.getEmail());//邮箱
                    baseUser.setUserhead(info.getUserhead());//用户头像
                    baseUser.setUserid(String.valueOf(info.getUserid()));//用户ID

                    ApplicationContext.getInstance().setBaseUser(baseUser);
                    UserPreference.save(KeyConstance.IS_USER_ID, String.valueOf(info.getUserid()));
                    UserPreference.save(KeyConstance.IS_USER_ACCOUNT, info.getName());
                    UserPreference.save(KeyConstance.IS_USER_EMAIL, info.getEmail());
                    UserPreference.save(KeyConstance.IS_USER_PASSWORD, mEditPassword.getText().toString());

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    RegisterActivity.this.startActivity(intent);

                    Toast.makeText(this, "注册成功，正在返回登陆页面...", Toast.LENGTH_SHORT).show();

                    RegisterActivity.this.finish();
                } else {
                    Toast.makeText(this, "很遗憾！注册失败...", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onFailure(String apiName, Throwable throwable, int errorNo, String strMsg) {
        Toast.makeText(this, "Failure...", Toast.LENGTH_SHORT).show();
    }

}