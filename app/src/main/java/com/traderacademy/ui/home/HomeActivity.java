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

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;
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
public class HomeActivity extends BaseActivity implements BaseActivity.onLeftTextClick {
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    private BottomBar mBottomBar;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initViewPager();
        createBottomBar(savedInstanceState);
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
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomBar.selectTabAtPosition(position, true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void createBottomBar(Bundle savedInstanceState) {
        mBottomBar = BottomBar.attach(viewPager, savedInstanceState);
        mBottomBar.setItemsFromMenu(R.menu.bottombar_tabs, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                switch (menuItemId) {
                    case R.id.bb_menu_recents:
                        viewPager.setCurrentItem(0);
                        setCenterTextView(R.string.home);
                        break;
                    case R.id.bb_menu_favorites:
                        viewPager.setCurrentItem(1);
                        setCenterTextView(R.string.learn);
                        break;
                    case R.id.bb_menu_nearby:
                        viewPager.setCurrentItem(2);
                        setCenterTextView(R.string.exam);
                        break;
                    case R.id.bb_menu_friends:
                        viewPager.setCurrentItem(3);
                        setCenterTextView(R.string.user);
                        break;

                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }
        });
        mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.colorAccent));
        mBottomBar.mapColorForTab(1, 0xFF5D4037);
        mBottomBar.mapColorForTab(2, "#7B1FA2");
        mBottomBar.mapColorForTab(3, "#FF4081");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mBottomBar.onSaveInstanceState(outState);
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
    }


    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == R.id.notice) {
            ToastUtils.setToastContext(getApplicationContext(),"点击公告");
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
        ToastUtils.setToastContext(getApplicationContext(),"点击签到");
    }
}
