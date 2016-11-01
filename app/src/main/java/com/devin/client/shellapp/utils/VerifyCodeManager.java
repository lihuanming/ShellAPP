package com.devin.client.shellapp.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.devin.client.shellapp.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 项目名称：ShellApp
 * 类描述：短信验证码操作类
 * 创建人：LHM
 * 创建时间：2016/11/1 1:36
 * 修改人：LHM
 * 修改时间：2016/11/1 1:36
 * 修改备注：
 */

public class VerifyCodeManager {
    public final static int REGISTER = 1;           //注册
    public final static int RESET_PWD = 2;          //修改密码
    public final static int BIND_PHONE = 3;         //绑定手机

    private Context mContext;
    private int recLen = 60;
    private Timer timer = new Timer();
    private Handler mHandler = new Handler();
    private String phone;

    private EditText phoneEdit;
    private Button getVerifiCodeButton;

    public VerifyCodeManager(Context context, EditText editText, Button btn) {
        this.mContext = context;
        this.phoneEdit = editText;
        this.getVerifiCodeButton = btn;
    }

    public void getVerifyCode(int type) {
        // 获取验证码之前先判断手机号

        phone = phoneEdit.getText().toString().trim();

        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(mContext, R.string.tip_please_input_phone, Toast.LENGTH_SHORT).show();
            return;
        } else if (phone.length() < 11) {
            Toast.makeText(mContext, R.string.tip_phone_regex_not_right, Toast.LENGTH_SHORT).show();
            return;
        } else if (!ValidateUserInfo.isMobileValid(phone)) {
            Toast.makeText(mContext, R.string.tip_phone_regex_not_right, Toast.LENGTH_SHORT).show();
            return;
        }

        /**
         * @param string 电话号码的区号 比如说86
         * @param string 具体的电话号码
         */
        // 两种方式：1.集成第三方SDK，调用sdk的方法获取验证码
        //SMSSDK.getVerificationCode("86", phone);


        //2. 请求服务端，由服务端为客户端发送验证码
//		HttpRequestHelper.getInstance().getVerifyCode(mContext, phone, type,
//				getVerifyCodeHandler);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        setButtonStatusOff();
                        if (recLen < 1) {
                            setButtonStatusOn();
                        }
                    }
                });
            }
        };

        timer = new Timer();
        timer.schedule(task, 0, 1000);

    }

    @SuppressLint("StringFormatMatches")
    private void setButtonStatusOff() {
        getVerifiCodeButton.setText(String.format(
                mContext.getResources().getString(R.string.count_down), recLen--));
        getVerifiCodeButton.setClickable(false);
        getVerifiCodeButton.setTextColor(Color.parseColor("#f3f4f8"));
        getVerifiCodeButton.setBackgroundColor(Color.parseColor("#b1b1b3"));
    }

    private void setButtonStatusOn() {
        timer.cancel();
        getVerifiCodeButton.setText("重新发送");
        getVerifiCodeButton.setTextColor(Color.parseColor("#b1b1b3"));
        getVerifiCodeButton.setBackgroundColor(Color.parseColor("#f3f4f8"));
        recLen = 60;
        getVerifiCodeButton.setClickable(true);
    }

}
