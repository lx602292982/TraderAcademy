package com.traderacademy.ui.home;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.RadioGroup;

import com.traderacademy.R;
import com.traderacademy.supprot.utils.ToastUtils;
import com.traderacademy.ui.BaseActivity;
import com.traderacademy.ui.exam.ExamFragment;
import com.traderacademy.ui.learn.LearmFragment;
import com.traderacademy.ui.user.UserFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lixiang on 16/8/26.
 */
public class HomeActivity extends BaseActivity implements ViewPager.OnPageChangeListener,BaseActivity.onLeftTextClick, RadioGroup.OnCheckedChangeListener {
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    RadioGroup radioGroup;


    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initViewPager();
    }

    private void initViewPager() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new LearmFragment());
        fragmentList.add(new ExamFragment());
        fragmentList.add(new UserFragment());

        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        //ViewPager显示第一个Fragment
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(this);
    }


    @Override
    public void setToolBar(Toolbar bar) {
        setOnLeftClickListener(this);
        setCenterTextView(R.string.home);
        setLeftTextView("签到");
    }

    @Override
    public int setContentView() {
        return R.layout.activity_home;
    }

    @Override
    public void init() {
        radioGroup  = (RadioGroup)findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);
    }


    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == R.id.notice) {
            ToastUtils.setToastContext(getApplicationContext(), "点击公告");
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onLeftClick() {
        ToastUtils.setToastContext(getApplicationContext(), "点击签到");
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rd_home:
                viewPager.setCurrentItem(0, false);
                setCenterTextView(R.string.home);
                break;
            case R.id.rd_learn:
                viewPager.setCurrentItem(1, false);
                setCenterTextView(R.string.learn);
                break;
            case R.id.rd_exam:
                viewPager.setCurrentItem(2, false);
                setCenterTextView(R.string.exam);
                break;
            case R.id.rd_user:
                viewPager.setCurrentItem(3, false);
                setCenterTextView(R.string.user);
                break;
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                radioGroup.check(R.id.rd_home);
                break;
            case 1:
                radioGroup.check(R.id.rd_learn);
                break;
            case 2:
                radioGroup.check(R.id.rd_exam);
                break;
            case 3:
                radioGroup.check(R.id.rd_user);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
