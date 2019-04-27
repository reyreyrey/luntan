package com.android.tuan.ui;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.mj.model.UserModel;
import com.android.mj.tools.ToastUtils;
import com.android.mj.tools.activity_manager.ActivityManager;
import com.android.mj.ui.UIActivity;
import com.android.tuan.R;
import com.android.tuan.adapter.MainPagerAdapter;
import com.android.tuan.databinding.ActivityMainBinding;
import com.avos.avoscloud.AVUser;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import static com.android.tuan.tools.Tools.changeDrawableToDefaultTheme;
import static com.android.tuan.tools.Tools.getTabButtonDrawable;


public class MainActivity extends UIActivity<ActivityMainBinding> implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private MainPagerAdapter adapter;
    private UserModel userModel;
    private long backTime;
    private int tempClickId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        EventBus.getDefault().register(this);
        tvBack.setVisibility(View.GONE);
        databinding.rb1.setChecked(true);
        tempClickId = R.id.rb_1;
        adapter = new MainPagerAdapter(context);
        databinding.viewpager.setAdapter(adapter);
        databinding.viewpager.setOffscreenPageLimit(adapter.getCount());
        databinding.radiogroup.setOnCheckedChangeListener(this);
        databinding.imgFabu.setOnClickListener(this);

        databinding.rb1.setCompoundDrawablesWithIntrinsicBounds(null, getTabButtonDrawable(R.drawable.ic_index_n), null, null);
        databinding.rb2.setCompoundDrawablesWithIntrinsicBounds(null, getTabButtonDrawable(R.drawable.ic_tuijian_n), null, null);
        databinding.rb3.setCompoundDrawablesWithIntrinsicBounds(null, getTabButtonDrawable(R.drawable.ic_faxian_n), null, null);
        databinding.rb4.setCompoundDrawablesWithIntrinsicBounds(null, getTabButtonDrawable(R.drawable.ic_mine_n), null, null);
        databinding.imgFabu.setImageDrawable(changeDrawableToDefaultTheme(getResources().getDrawable(R.drawable.ic_main_fabu)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscriber(tag = "changeTab")
    public void changeTab(int pos) {
        RadioButton radioButton = (RadioButton) databinding.radiogroup.getChildAt(pos);
        radioButton.setChecked(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (backTime == 0) {
                backTime = System.currentTimeMillis();
                ToastUtils.toastWarn(this, getString(R.string.hybrid_exit_app));
                return true;
            }
            if ((System.currentTimeMillis() - backTime) >= 2000) {
                backTime = System.currentTimeMillis();
                ToastUtils.toastWarn(this, getString(R.string.hybrid_exit_app));
                return true;
            }
            ActivityManager.exitApp();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (i) {
            case R.id.rb_1:
                tempClickId = R.id.rb_1;
                databinding.viewpager.setCurrentItem(0);
                break;
            case R.id.rb_2:
                tempClickId = R.id.rb_2;
                databinding.viewpager.setCurrentItem(1);
                break;
            case R.id.rb_3:
                userModel = AVUser.getCurrentUser(UserModel.class);
                if (userModel == null) {
                    RadioButton radioButton = (RadioButton) findViewById(tempClickId);
                    radioButton.setChecked(true);
                    startActivity(new Intent(context, LoginActivity.class));
                } else {
                    tempClickId = R.id.rb_3;
                    databinding.viewpager.setCurrentItem(2);
                }
                break;
            case R.id.rb_4:
                userModel = AVUser.getCurrentUser(UserModel.class);
                if (userModel == null) {
                    RadioButton radioButton = (RadioButton) findViewById(tempClickId);
                    radioButton.setChecked(true);
                    startActivity(new Intent(context, LoginActivity.class));
                } else {
                    tempClickId = R.id.rb_4;
                    databinding.viewpager.setCurrentItem(3);
                }
                break;
        }
        supportInvalidateOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        refreshMenu(menu);
        return super.onPrepareOptionsMenu(menu);
    }

    private void refreshMenu(Menu menu) {
        if (databinding.radiogroup.getCheckedRadioButtonId() == R.id.rb_4) {
            getMenuInflater().inflate(R.menu.menu_setting, menu);
        }
        refreshTitle();
    }

    private void refreshTitle() {
        RadioButton button = (RadioButton) findViewById(databinding.radiogroup.getCheckedRadioButtonId());
        tvTitle.setText(button.getText());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting:
                startActivity(new Intent(context, SettingActivity.class));
                break;
            case R.id.search:
                startActivity(new Intent(context, SearchActivity.class));
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        userModel = AVUser.getCurrentUser(UserModel.class);
        if (userModel == null) {
            startActivity(new Intent(context, LoginActivity.class));
            return;
        }
        startActivity(new Intent(context, AddPostActivity.class));
    }
}
