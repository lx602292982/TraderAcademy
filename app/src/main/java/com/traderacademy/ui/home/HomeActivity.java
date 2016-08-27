package com.traderacademy.ui.home;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;
import com.traderacademy.R;
import com.traderacademy.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by lixiang on 16/8/26.
 */
public class HomeActivity extends BaseActivity {

    private BottomBar mBottomBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        createBottomBar(savedInstanceState);

    }

    private void createBottomBar(Bundle savedInstanceState) {
        mBottomBar = (BottomBar)findViewById(R.id.bottomBar);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mBottomBar.onSaveInstanceState(outState);
    }

    @Override
    public void setToolBar(Toolbar bar) {
        setCenterTextView(R.string.app_name);
    }

    @Override
    public int setContentView() {
        return R.layout.activity_home;
    }

    @Override
    public void init() {


    }
}
