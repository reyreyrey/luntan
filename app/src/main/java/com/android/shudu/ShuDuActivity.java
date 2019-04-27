package com.android.shudu;

import com.android.mj.R;
import com.android.mj.databinding.ActivityShuduBinding;
import com.android.mj.ui.UIActivity;

public class ShuDuActivity extends UIActivity<ActivityShuduBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_shudu;
    }

    @Override
    protected void init() {
        tvTitle.setText("数独");
    }
}
