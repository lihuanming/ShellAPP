package com.devin.client.shellapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.devin.client.shellapp.R;
import com.devin.client.shellapp.ui.activity.LoginActivity;
import com.devin.client.shellapp.ui.activity.StartMainActivity;
import com.devin.client.shellapp.ui.activity.UserInfoActivity;
import com.devin.client.shellapp.ui.activity.UserMainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


public class UserInfoFragment extends Fragment {


    @Bind(R.id.header)
    CircleImageView mHeader;
    @Bind(R.id.user_info)
    RelativeLayout mUserInfo;
    @Bind(R.id.user_goods)
    RelativeLayout mUserGoods;
    @Bind(R.id.start)
    RelativeLayout mStart;
    @Bind(R.id.nickname)
    TextView mNickname;
    @Bind(R.id.user_Id)
    TextView mUserId;

    public static UserInfoFragment newInstance() {

        UserInfoFragment fragment = new UserInfoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public UserInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_info, container, false);

        ButterKnife.bind(this, view);
        mHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(getActivity(), UserInfoActivity.class);
                getActivity().startActivity(intent);*/
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(intent);
            }
        });

        mUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserMainActivity.class);
                getActivity().startActivity(intent);
            }
        });

        mUserGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserInfoActivity.class);
                getActivity().startActivity(intent);
            }
        });

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StartMainActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
