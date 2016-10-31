package com.devin.client.shellapp.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devin.client.shellapp.R;
import com.devin.client.shellapp.utils.ShareUtils;

import butterknife.Bind;
import butterknife.ButterKnife;


public class HongKeFragment extends Fragment {

    @Bind(R.id.good_tip)
    ImageView goodTip;
    @Bind(R.id.good_tip_num)
    TextView goodTipNum;
    @Bind(R.id.share)
    ImageView share;
    @Bind(R.id.comment)
    ImageView comment;
    @Bind(R.id.comment_num)
    TextView commentNum;

    public HongKeFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static HongKeFragment newInstance() {
        HongKeFragment fragment = new HongKeFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_hong_ke, container, false);
        ButterKnife.bind(this, view);

        goodTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodTip.setImageResource(R.mipmap.goodtip_select);
                int num = Integer.valueOf(goodTipNum.getText().toString());
                num++;
                goodTipNum.setText(num+"");
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtils.share(getActivity());
            }
        });

        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
