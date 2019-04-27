package com.android.tuan.ui;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import com.android.mj.model.Post;
import com.android.tuan.R;
import com.android.tuan.adapter.HomeAdapter;
import com.android.tuan.databinding.ActivityRefreshListviewBinding;
import com.android.tuan.databinding.HeaderTypePostBinding;
import com.android.tuan.models.PostType;
import com.avos.avoscloud.AVQuery;
import com.joanzapata.android.QuickAdapter;

/**
 * author: Rea.X
 * date: 2017/12/14.
 */

public class PostTypeActivity extends RefreshBaseActivity<ActivityRefreshListviewBinding, Post> {
    private HeaderTypePostBinding headerTypePostBinding;
    private int type;

    public static void postType(Context context, int type) {
        Intent intent = new Intent(context, PostTypeActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    protected void init() {
        super.init();
        type = getIntent().getIntExtra("type", -1);
        headerTypePostBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.header_type_post, null, false);
        listView.addHeaderView(headerTypePostBinding.getRoot());
        setImageAndText();
    }

    /**
     * "新手报道", 1
     * 签到区2
     * "彩票专区", 3
     * "灌水区",4
     * "精华区",5
     * "经验交流",6
     * "技术分享",7
     */

    private void setImageAndText() {
        switch (type) {
            case 1:
                tvTitle.setText("新手报道");
                headerTypePostBinding.tvHeader.setText("新手报道");
                headerTypePostBinding.tvHeader.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_xinshou, 0, 0);
                break;
            case 2:
                tvTitle.setText("签到区");
                headerTypePostBinding.tvHeader.setText("签到区");
                headerTypePostBinding.tvHeader.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_qiandao, 0, 0);
                break;
            case 3:
                tvTitle.setText("彩票专区");
                headerTypePostBinding.tvHeader.setText("彩票专区");
                headerTypePostBinding.tvHeader.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_caipiao, 0, 0);
                break;
            case 4:
                tvTitle.setText("灌水区");
                headerTypePostBinding.tvHeader.setText("灌水区");
                headerTypePostBinding.tvHeader.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_guanshui, 0, 0);
                break;
            case 5:
                tvTitle.setText("精华区");
                headerTypePostBinding.tvHeader.setText("精华区");
                headerTypePostBinding.tvHeader.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_jinghua, 0, 0);
                break;
            case 6:
                tvTitle.setText("经验交流");
                headerTypePostBinding.tvHeader.setText("经验交流");
                headerTypePostBinding.tvHeader.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_jiaoliu, 0, 0);
                break;
            case 7:
                tvTitle.setText("技术分享");
                headerTypePostBinding.tvHeader.setText("技术分享");
                headerTypePostBinding.tvHeader.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_fenxiang, 0, 0);
                break;
        }
    }

    @Override
    protected Class<Post> getQueryClass() {
        return Post.class;
    }

    @Override
    protected AVQuery getAvQuery(AVQuery avQuery) {
        avQuery.whereEqualTo("type", type);
        avQuery.include("user");
        return avQuery;
    }

    @Override
    protected QuickAdapter<Post> getAdapter() {
        return new HomeAdapter(context);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        super.onItemClick(adapterView, view, i, l);
        PostDetailActivity.seePost(context, adapter.getItem(i - 1));
    }
}
