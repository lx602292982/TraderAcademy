package com.traderacademy.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.traderacademy.R;
import com.traderacademy.supprot.utils.ScreenUtils;

/**
 * Created by lixiang on 16/8/26.
 */
public abstract class BaseActivity extends AppCompatActivity {

    public FrameLayout main;
    private View content;
    public Toolbar bar;
    private int statusBarHeight = 0;
    private TextView centerText;
    public View barDivider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        main = (FrameLayout) findViewById(R.id.main);
        centerText = (TextView) findViewById(R.id.centerText);

        bar = (Toolbar) findViewById(R.id.toolbar);
        barDivider = findViewById(R.id.toolbar_divider);
        if (setContentView() != 0) {
            content = getLayoutInflater()
                    .inflate(setContentView(), null, false);
        }
        View contentView = getContentView();
        if (contentView != null) {
            content = contentView;
        }
        if (content != null) {
            main.addView(content, 0, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }

        bar.setTitle("");
        bar.setTitleTextAppearance(this, R.style.ToolBarTitle);
        bar.setBackgroundResource(R.color.colorAccent);
        resetToolBar();
        setToolBar(bar);
        setSupportActionBar(bar);
        registerButtonListener();
        init();

    }

    public void registerButtonListener() {
        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNavigationClick();
            }
        });
        centerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCenterTextClick();
            }
        });

    }

    public void addView(View child, int index) {
        main.addView(child, index);
    }


    public void setBackgroundAlpha(float alpha) {
        Drawable drawable = bar.getBackground();
        Drawable drawable2 = barDivider.getBackground();
        drawable.setAlpha((int) alpha);
        drawable2.setAlpha((int) alpha);
    }

    public void setLayoutMargin(int barMargin, int contentMargin) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) bar.getLayoutParams();
        params.topMargin = barMargin;
        bar.setLayoutParams(params);
        if (content != null) {
            ViewGroup.MarginLayoutParams cparams = (ViewGroup.MarginLayoutParams) content
                    .getLayoutParams();
            cparams.topMargin = contentMargin;
            content.setLayoutParams(cparams);
        }
    }

    public void hideToolBar() {
        this.resetToolBar();
        this.setCenterTextView("");
        this.setBackgroundAlpha(0.0f);
        this.setFullscreen();
    }

    public void setFullscreen() {
        setLayoutMargin(0, 0);
        // setStatusBarColor(getResources().getColor(android.R.color.transparent));
        bar.setVisibility(View.GONE);
        barDivider.setVisibility(View.GONE);
    }

    public void setCenterTextView(int resId) {
        setCenterTextView(getResources().getString(resId));
    }

    public void setCenterTextView(String text) {
        centerText.setVisibility(View.VISIBLE);
        centerText.setText(text);
    }

    public void setCenterTextRightDrawble(int resId) {
        Drawable drawable = getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                drawable.getMinimumHeight());
        centerText.setCompoundDrawables(null, null, drawable, null);
    }

    public void addCenterView(View view) {
        bar.removeAllViews();
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        bar.addView(view);
    }

    public void resetToolBar() {
        bar.setVisibility(View.VISIBLE);
        barDivider.setVisibility(View.VISIBLE);
        // setStatusBarColor(R.color.white_title);
        bar.setBackgroundResource(R.color.colorAccent);
        setBackgroundAlpha(255);
        // setStatusBarAlpha(1f);
        setLayoutMargin(statusBarHeight,
                statusBarHeight + ScreenUtils.dip2px(this, 48));

        centerText.setVisibility(View.VISIBLE);

    }

    public TextView getCenterText() {
        return centerText;
    }

    public int getStatusBarHeight() {
        return statusBarHeight;
    }

    public Toolbar getToolBar() {
        return bar;
    }

    public void onNavigationClick() {
    }

    public void onRightButtonClick() {
    }

    public void onCenterTextClick() {
    };

    public void onSearchViewClick() {

    }

    public abstract void setToolBar(Toolbar bar);

    public abstract int setContentView();

    public abstract void init();

    public View getContentView() {
        return null;
    }
}
