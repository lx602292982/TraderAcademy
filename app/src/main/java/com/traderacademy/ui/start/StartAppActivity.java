package com.traderacademy.ui.start;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;

import com.traderacademy.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StartAppActivity extends AppCompatActivity {


    @Bind(R.id.activity_main)
    LinearLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(1800);
        activityMain.startAnimation(animation);

        jumpActivity(new Intent(this, LoginActivity.class));

    }

    public void jumpActivity(final Intent intent) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                StartAppActivity.this.finish();
            }
        }, 2000);
    }
}
