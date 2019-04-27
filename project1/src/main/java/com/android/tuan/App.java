package com.android.tuan;

import android.app.Application;
import android.content.Context;

import com.android.mj.Library;
import com.android.tuan.models.PostType;
import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.header.WaveSwipeHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Rea.X
 * date: 2017/12/11.
 */

public class App extends Application {
    public static final List<PostType> postType;
    public static final String[] TYPES = {
            "新手报道",
            "签到区",
            "彩票专区",
            "灌水区",
            "精华区",
            "经验交流",
            "技术分享",
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Library.init(this, BuildConfig.BMOB_KEY, BuildConfig.DEBUG);
    }

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new WaveSwipeHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    static {
        postType = new ArrayList<>();
        postType.add(new PostType(1, TYPES[0]));
        postType.add(new PostType(2, TYPES[1]));
        postType.add(new PostType(3, TYPES[2]));
        postType.add(new PostType(4, TYPES[3]));
        postType.add(new PostType(5, TYPES[4]));
        postType.add(new PostType(6, TYPES[5]));
        postType.add(new PostType(7, TYPES[6]));
    }
}
