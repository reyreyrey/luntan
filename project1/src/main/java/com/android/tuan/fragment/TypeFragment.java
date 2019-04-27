package com.android.tuan.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

import com.android.mj.ui.UIBaseFragment;
import com.android.tuan.R;
import com.android.tuan.databinding.FragmentHomeBinding;
import com.android.tuan.databinding.FragmentTypeBinding;
import com.android.tuan.ui.PostTypeActivity;

/**
 * author: Rea.X
 * date: 2017/12/13.
 */

public class TypeFragment extends UIBaseFragment<FragmentTypeBinding> implements View.OnClickListener {
    @Override
    protected void lazyLoad() {

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_type;
    }

    @Override
    protected void init(View view) {
        databinding.tvCaipiao.setOnClickListener(this);
        databinding.tvFenxiang.setOnClickListener(this);
        databinding.tvGuanshui.setOnClickListener(this);
        databinding.tvJiaoliu.setOnClickListener(this);
        databinding.tvJinghua.setOnClickListener(this);
        databinding.tvQiandao.setOnClickListener(this);
        databinding.tvXinshou.setOnClickListener(this);
    }

    public static Fragment getInstant() {
        return new TypeFragment();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_xinshou:
                PostTypeActivity.postType(getContext(), 1);
                break;
            case R.id.tv_qiandao:
                PostTypeActivity.postType(getContext(), 2);
                break;
            case R.id.tv_caipiao:
                PostTypeActivity.postType(getContext(), 3);
                break;
            case R.id.tv_guanshui:
                PostTypeActivity.postType(getContext(), 4);
                break;
            case R.id.tv_jinghua:
                PostTypeActivity.postType(getContext(), 5);
                break;
            case R.id.tv_jiaoliu:
                PostTypeActivity.postType(getContext(), 6);
                break;
            case R.id.tv_fenxiang:
                PostTypeActivity.postType(getContext(), 7);
                break;
        }
    }
}
