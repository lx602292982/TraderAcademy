package com.traderacademy.ui.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.traderacademy.R;
import com.traderacademy.ui.home.HomeFragment;

/**
 * 作者：lixiang on 2016/8/27 11:40
 * 邮箱：xiang.li@spreadwin.com
 */
public class UserFragment extends Fragment {
    private static final String ARG_POSITION = "position";

    private int position;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user, container, false);
        return v;
    }

    public static HomeFragment newInstance(int position) {
        HomeFragment f = new HomeFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }
}
