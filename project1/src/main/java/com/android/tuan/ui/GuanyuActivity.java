package com.android.tuan.ui;

import com.android.mj.ui.UIActivity;
import com.android.tuan.R;
import com.android.tuan.databinding.ActivityGuanyuBinding;

/**
 * author: Rea.X
 * date: 2017/12/14.
 */

public class GuanyuActivity extends UIActivity<ActivityGuanyuBinding>{
    @Override
    protected int getLayoutId() {
        return R.layout.activity_guanyu;
    }

    @Override
    protected void init() {
        tvTitle.setText("关于我们");
    }
}
