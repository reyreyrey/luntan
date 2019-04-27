package com.android.tuan.jiakao;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间：2019/4/17
 * 方法编写人：Rea.X
 * 功能描述：
 */
public class JiakaoAdapter extends FragmentPagerAdapter {
    private List<ShitiModel> list = new ArrayList<>();

    public JiakaoAdapter(FragmentManager fm) {
        super(fm);

    }

    public void setData(List<ShitiModel> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        ShitiFragment shitiFragment = new ShitiFragment();
        shitiFragment.setData(list.get(position), list.size(), position);
        return shitiFragment;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
