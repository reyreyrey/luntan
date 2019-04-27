package com.android.tuan.jiakao;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.android.mj.tools.LogUtil;
import com.android.mj.tools.ToastUtils;
import com.android.mj.ui.UIBaseFragment;
import com.android.tuan.R;
import com.android.tuan.databinding.FragmentShitiBinding;
import com.bumptech.glide.Glide;
import com.yhd.swfplayer.SwfPlayerHelper;

import static cn.bmob.v3.Bmob.getApplicationContext;
import static com.android.tuan.jiakao.CheckResultTools.getRightAnswer;
import static com.lzy.okgo.utils.HttpUtils.runOnUiThread;

/**
 * 创建时间：2019/4/17
 * 方法编写人：Rea.X
 * 功能描述：
 */
public class ShitiFragment extends UIBaseFragment<FragmentShitiBinding> implements View.OnClickListener {
    private ShitiModel model;
    private int size, position;
    @Override
    protected void lazyLoad() {

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_shiti;
    }

    private void playImage(WebView webView, String url){
        SwfPlayerHelper.getInstance(getContext())
                .setWebView(webView)//设置flash播放的载体
                .playSwf(url);//传入绝对路径、带file://的绝对路径、url都行
    }
    @Override
    protected void init(View view) {
        WebSettings webSettings = databinding.wvImage.getSettings();
        webSettings.setJavaScriptEnabled(true);  //JS可用
        webSettings.setPluginState(WebSettings.PluginState.ON);//设置adobe插件可用
        databinding.btnSubmit.setOnClickListener(this);
        databinding.tvSeeJieshi.setOnClickListener(this);
        databinding.tvQuest.setText("("+(position+1)+"/"+size+")"+model.getQuestion());
        String url = model.getUrl();
        if(TextUtils.isEmpty(url)){
            databinding.rlImage.setVisibility(View.GONE);
        }else{
            databinding.rlImage.setVisibility(View.VISIBLE);
            if(url.endsWith("swf")){
                LogUtil.e("url:::"+url);
                databinding.wvImage.setVisibility(View.VISIBLE);
                playImage(databinding.wvImage, url);
            }else{
                databinding.ivImage.setVisibility(View.VISIBLE);
                Glide.with(this).asBitmap().load(url).into(databinding.ivImage);
            }
        }
        String item1 = model.getItem1();
        String item2 = model.getItem2();
        String item3 = model.getItem3();
        String item4 = model.getItem4();
        databinding.rb1.setVisibility(TextUtils.isEmpty(item1) ? View.GONE : View.VISIBLE);
        databinding.rb2.setVisibility(TextUtils.isEmpty(item2) ? View.GONE : View.VISIBLE);
        databinding.rb3.setVisibility(TextUtils.isEmpty(item3) ? View.GONE : View.VISIBLE);
        databinding.rb4.setVisibility(TextUtils.isEmpty(item4) ? View.GONE : View.VISIBLE);
        databinding.rb1.setText(item1);
        databinding.rb2.setText(item2);
        databinding.rb3.setText(item3);
        databinding.rb4.setText(item4);
    }

    public void setData(ShitiModel model, int size, int position){
        this.model = model;
        this.size = size;
        this.position = position;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_submit){
            if(!databinding.rb1.isChecked()
                    &&!databinding.rb2.isChecked()
                    &&!databinding.rb3.isChecked()
                    &&!databinding.rb4.isChecked()
            ){
                return;
            }
            boolean isRight = CheckResultTools.isRight(databinding.rb1, databinding.rb2, databinding.rb3, databinding.rb4, model.getAnswer());
            if(isRight){
                ToastUtils.toastSuccess(getContext(), "回答正确");
            }else{
                databinding.tvJieshi.setText("");
                databinding.tvJieshi.append("正确答案是："+getRightAnswer(model.getAnswer()));
                databinding.tvJieshi.append("\n");
                databinding.tvJieshi.setVisibility(View.VISIBLE);
                databinding.tvJieshi.append(model.getExplains());
                return;
            }
            Activity activity = getActivity();
            if(activity instanceof JiaKaoActivity){
                JiaKaoActivity jiaKaoActivity = (JiaKaoActivity) activity;
                jiaKaoActivity.showNext();
            }
        }

        if(view.getId() == R.id.tv_see_jieshi){
            if(databinding.tvJieshi.getVisibility() == View.VISIBLE){
                databinding.tvJieshi.setVisibility(View.GONE);
            }else{
                databinding.tvJieshi.setText("");
                databinding.tvJieshi.append("正确答案是："+getRightAnswer(model.getAnswer()));
                databinding.tvJieshi.append("\n");
                databinding.tvJieshi.setVisibility(View.VISIBLE);
                databinding.tvJieshi.append(model.getExplains());
            }
        }

    }
}
