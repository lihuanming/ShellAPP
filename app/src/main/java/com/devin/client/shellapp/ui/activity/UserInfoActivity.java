package com.devin.client.shellapp.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.devin.client.shellapp.R;
import com.devin.client.shellapp.utils.UserPreference;
import com.devin.client.shellapp.utils.constant.KeyConstance;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 书凡 on 2015-11-30.
 */
public class UserInfoActivity extends AppCompatActivity {

    @Bind(R.id.number)
    TextView mNumber;
    @Bind(R.id.header)
    ImageView mHeader;
    @Bind(R.id.username)
    TextView mUsername;
    @Bind(R.id.twodimensioncode)
    ImageView mTwodimensioncode;
    @Bind(R.id.sex)
    TextView mSex;
    @Bind(R.id.adress)
    TextView mAdress;
    @Bind(R.id.person_message)
    TextView mPersonMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_person_info);
        ButterKnife.bind(this);
        mNumber.setText(UserPreference.read(KeyConstance.IS_USER_PHONE, "0"));
        initView();
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initView() {
        mHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(UserInfoActivity.this,
                        SelectPicPopupWindow.class), 1);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (resultCode) {
            case 1:
                if (data != null) {
                    //取得返回的Uri,基本上选择照片的时候返回的是以Uri形式，但是在拍照中有得机子呢Uri是空的，所以要特别注意
                    Uri mImageCaptureUri = data.getData();
                    //返回的Uri不为空时，那么图片信息数据都会在Uri中获得。如果为空，那么我们就进行下面的方式获取
                    if (mImageCaptureUri != null) {
                        Bitmap image;
                        try {
                            //这个方法是根据Uri获取Bitmap图片的静态方法
                            image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mImageCaptureUri);
                            if (image != null) {
                                mHeader.setImageBitmap(image);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Bundle extras = data.getExtras();
                        if (extras != null) {
                            //这里是有些拍照后的图片是直接存放到Bundle中的所以我们可以从这里面获取Bitmap图片
                            Bitmap image = extras.getParcelable("data");
                            if (image != null) {
                                mHeader.setImageBitmap(image);
                            }
                        }
                    }

                }
                break;
            default:
                break;

        }
    }
}
