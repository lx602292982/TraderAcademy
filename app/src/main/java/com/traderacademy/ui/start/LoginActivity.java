package com.traderacademy.ui.start;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.traderacademy.R;
import com.traderacademy.supprot.view.ResizeLayout;
import com.traderacademy.ui.BaseActivity;
import com.traderacademy.ui.home.HomeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by lixiang on 16/8/26.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.phone_text)
    TextView phoneText;
    @Bind(R.id.phone_num)
    EditText phoneNum;
    @Bind(R.id.pwd_text)
    TextView pwdText;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.login)
    TextView login;
    @Bind(R.id.scroll)
    ScrollView scroll;
    @Bind(R.id.resize)
    ResizeLayout resize;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

    }

    @Override
    public void setToolBar(Toolbar bar) {
        setCenterTextView(R.string.login);
    }

    @Override
    public int setContentView() {
        return R.layout.activity_login;
    }

    @Override
    public void init() {
    }

    @Override
    public void onClick(View v) {
    }

    public void login(View v){
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
    }
}
