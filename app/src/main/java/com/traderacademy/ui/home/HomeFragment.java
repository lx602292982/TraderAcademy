package com.traderacademy.ui.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.traderacademy.R;
import com.traderacademy.supprot.view.Kanner;

import butterknife.ButterKnife;

/**
 * Created by lixiang on 16/8/26.
 */
public class HomeFragment extends Fragment {
    private static final String ARG_POSITION = "position";

    private int position;


    Kanner kanner;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        kanner = (Kanner) v.findViewById(R.id.kanner);
//        kanner.setImagesUrl(new String[]{
//                "http://img04.muzhiwan.com/2015/06/16/upload_557fd293326f5.jpg",
//                "http://img03.muzhiwan.com/2015/06/05/upload_557165f4850cf.png",
//                "http://img02.muzhiwan.com/2015/06/11/upload_557903dc0f165.jpg",
//                "http://img04.muzhiwan.com/2015/06/05/upload_5571659957d90.png",
//                "http://img03.muzhiwan.com/2015/06/16/upload_557fd2a8da7a3.jpg"});
//    }
        int[] imagesRes = {R.mipmap.image1, R.mipmap.iamge2, R.mipmap.iamge3,
                R.mipmap.iamge4, R.mipmap.iamge5};
        kanner.setImagesRes(imagesRes);
    }

    public static HomeFragment newInstance(int position) {
        HomeFragment f = new HomeFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
