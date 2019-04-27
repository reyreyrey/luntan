package com.android.tuan.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.android.mj.net.callback.JsonCallback;
import com.android.mj.tools.GlideImageLoaderBanner;
import com.android.mj.tools.LogUtil;
import com.android.mj.ui.LuckPanelActivity;
import com.android.mj.ui.UIBaseFragment;
import com.android.mj.ui.WebViewActivity;
import com.android.mj.ui.lotto.lottery.LottoTrendActivity;
import com.android.shudu.ShuDuActivity;
import com.android.tuan.R;
import com.android.tuan.databinding.FragmentHome1Binding;
import com.android.tuan.jiakao.JiaKaoActivity;
import com.android.tuan.models.HistoryStoryModel;
import com.android.tuan.qiwenyishi.QiwenYishiActivity;
import com.android.tuan.ui.ArtActivity;
import com.android.tuan.ui.FuliActivity;
import com.android.tuan.ui.GuigushiActivity;
import com.android.tuan.ui.ManhuaListActivity;
import com.android.tuan.ui.TrendChartActivity;
import com.android.tuan.ui.XiaohuaCardActivity;
import com.android.tuan.ui.WXMeiwenActivity;
import com.android.tuan.ui.XiaohuaActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import static com.youth.banner.BannerConfig.NUM_INDICATOR_TITLE;

/**
 * author: Rea.X
 * date: 2017/12/13.
 * 九宫格样式，没有列表
 */

public class HomeFragment1 extends UIBaseFragment<FragmentHome1Binding> implements View.OnClickListener {


    @Override
    protected void lazyLoad() {

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_1;
    }

    @Override
    protected void init(View view) {
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.banner_jitang);
        images.add(R.drawable.banner_jishu);
        images.add(R.drawable.banner_gaoxiao);
        images.add(R.drawable.banner_manhua);
        images.add(R.drawable.banner_guigushi);
        List<String> titles = new ArrayList<>();
        titles.add("热乎乎的鸡汤");
        titles.add("艺术鉴赏");
        titles.add("忙了一天，来点开心的");
        titles.add("漫画");
        titles.add("来点刺激的提提神");
        databinding.banner.setImageLoader(new GlideImageLoaderBanner());
        databinding.banner.setDelayTime(3000);
        databinding.banner.setBannerStyle(NUM_INDICATOR_TITLE);
        databinding.banner.setImages(images);
        databinding.banner.setBannerTitles(titles);
        databinding.banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(getContext(), WXMeiwenActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getContext(), ArtActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getContext(), XiaohuaActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getContext(), ManhuaListActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(getContext(), GuigushiActivity.class));
                        break;
                }
            }
        });
        databinding.banner.start();
        databinding.tvOne.setOnClickListener(this);
        databinding.tvTwo.setOnClickListener(this);
        databinding.tvThree1.setOnClickListener(this);
        databinding.tvThree2.setOnClickListener(this);
        databinding.tvFour.setOnClickListener(this);
        databinding.tvFive.setOnClickListener(this);
        databinding.tvSix.setOnClickListener(this);
        databinding.tvSeven.setOnClickListener(this);
        databinding.tvEight.setOnClickListener(this);
        databinding.tvNine.setOnClickListener(this);

//        //图标颜色变成设置的主题色\
//        //开奖
//        databinding.tvOne.setCompoundDrawablesWithIntrinsicBounds(null, changeDrawableToDefaultTheme(getResources().getDrawable(R.drawable.ic_home_kaijiang)), null, null);
//        //艺术欣赏
//        databinding.tvTwo.setCompoundDrawablesWithIntrinsicBounds(null, changeDrawableToDefaultTheme(getResources().getDrawable(R.drawable.ic_home_art)), null, null);
//        //微信鸡汤
//        databinding.tvThree.setCompoundDrawablesWithIntrinsicBounds(null, changeDrawableToDefaultTheme(getResources().getDrawable(R.drawable.ic_home_weixinmeiwen)), null, null);
//        //每日一笑
//        databinding.tvFour.setCompoundDrawablesWithIntrinsicBounds(null, changeDrawableToDefaultTheme(getResources().getDrawable(R.drawable.ic_h_xiaohua)), null, null);
//        //走势图
//        databinding.tvFive.setCompoundDrawablesWithIntrinsicBounds(null, changeDrawableToDefaultTheme(getResources().getDrawable(R.drawable.ic_home_zoushi)), null, null);
//        //测试运气
//        databinding.tvSix.setCompoundDrawablesWithIntrinsicBounds(null, changeDrawableToDefaultTheme(getResources().getDrawable(R.drawable.ic_shouqi)), null, null);
//        //漫画
//        databinding.tvSeven.setCompoundDrawablesWithIntrinsicBounds(null, changeDrawableToDefaultTheme(getResources().getDrawable(R.drawable.ic_home_manhua)), null, null);
//        //鬼故事
//        databinding.tvEight.setCompoundDrawablesWithIntrinsicBounds(null, changeDrawableToDefaultTheme(getResources().getDrawable(R.drawable.ic_home_guigushi)), null, null);
//        //福利
//        databinding.tvNine.setCompoundDrawablesWithIntrinsicBounds(null, changeDrawableToDefaultTheme(getResources().getDrawable(R.drawable.ic_home_fuli)), null, null);

        OkGo.<List<HistoryStoryModel>>post("http://api.avatardata.cn/HistoryToday/LookUp")
                .params("yue", "4")
                .params("ri", "16")
                .params("type", "2")
                .params("key", "0d3e9ab16a5944659f573d0d063e6b09")
                .execute(new JsonCallback<List<HistoryStoryModel>>() {
                    @Override
                    public void onSuccess(Response<List<HistoryStoryModel>> response) {
                        List<HistoryStoryModel> list = response.body();
                        String[] ress = new String[list.size()];
                        for (int i = 0; i < list.size(); i++) {
                            ress[i] = list.get(i).getTitle();
                        }
                        databinding.tvHistoryLine.setResources(ress);
                        databinding.tvHistoryLine.setTextStillTime(2000);
                    }
                });
        OkGo.<String>get("http://apis.juhe.cn/cook/category?key=18ee9ce4ddd2525671461c9b592be29a")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtil.e("----===>"+response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        LogUtil.e("----===>"+response.getException().toString());
                    }
                });
    }


    public static Fragment getInstant() {
        return new HomeFragment1();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_one:
                startActivity(new Intent(getContext(), TrendChartActivity.class));
                break;
            case R.id.tv_two:
                startActivity(new Intent(getContext(), ArtActivity.class));
                break;
            case R.id.tv_three_1:
//                startActivity(new Intent(getContext(), QiwenYishiActivity.class));
                Intent intent = new Intent(getContext(), JiaKaoActivity.class);
                intent.putExtra("type", 1);
                startActivity(intent);
                break;
            case R.id.tv_three_2:
//                startActivity(new Intent(getContext(), QiwenYishiActivity.class));
                Intent intent1 = new Intent(getContext(), JiaKaoActivity.class);
                intent1.putExtra("type", 4);
                startActivity(intent1);
                break;
            case R.id.tv_four:
                startActivity(new Intent(getContext(), XiaohuaCardActivity.class));
                break;
            case R.id.tv_five:
                startActivity(new Intent(getContext(), LottoTrendActivity.class));
                break;
            case R.id.tv_six:
                startActivity(new Intent(getContext(), LuckPanelActivity.class));
                break;
            case R.id.tv_seven:
                WebViewActivity.load(getContext(), "https://www.kumaodm.com/", true);
//                startActivity(new Intent(getContext(), ManhuaListActivity.class));
                break;
            case R.id.tv_eight:
                startActivity(new Intent(getContext(), QiwenYishiActivity.class));
                break;
            case R.id.tv_nine:
                startActivity(new Intent(getContext(), ShuDuActivity.class));
                break;
        }
    }

}
