package com.android.tuan.ui;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.mj.net.callback.JsonCallback;
import com.android.mj.ui.UIActivity;
import com.android.tuan.R;
import com.android.tuan.databinding.FragmentSlideBinding;
import com.android.tuan.models.XiaohuaModel;
import com.dingmouren.layoutmanagergroup.slide.ItemConfig;
import com.dingmouren.layoutmanagergroup.slide.ItemTouchHelperCallback;
import com.dingmouren.layoutmanagergroup.slide.OnSlideListener;
import com.dingmouren.layoutmanagergroup.slide.SlideLayoutManager;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ViewPagerLayoutManager
 */
public class XiaohuaCardActivity extends UIActivity<FragmentSlideBinding> {
    private static final String TAG = "SlideFragment";
    private SlideLayoutManager mSlideLayoutManager;
    private ItemTouchHelper mItemTouchHelper;
    private ItemTouchHelperCallback mItemTouchHelperCallback;
    private MyAdapter mAdapter;
    private List<String> mList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_slide;
    }

    @Override
    protected void init() {
        tvTitle.setText("轻松时刻");
        databinding.smileView.setLike((int) (Math.random()*500));
        databinding.smileView.setDisLike((int) (Math.random()*500));

        mAdapter = new MyAdapter();
        databinding.recyclerView.setAdapter(mAdapter);
        mItemTouchHelperCallback = new ItemTouchHelperCallback(databinding.recyclerView.getAdapter(), mList);
        mItemTouchHelper = new ItemTouchHelper(mItemTouchHelperCallback);
        mSlideLayoutManager = new SlideLayoutManager(databinding.recyclerView, mItemTouchHelper);
        mItemTouchHelper.attachToRecyclerView(databinding.recyclerView);
        databinding.recyclerView.setLayoutManager(mSlideLayoutManager);
        initListener();
        query();
    }
    private int currentPage = (int) (Math.random()*10);
    private void query() {
        databinding.pb.setVisibility(View.VISIBLE);
        OkGo
                .<List<XiaohuaModel>>get("http://api.avatardata.cn/Joke/QueryJokeByTime")
                .params("time", String.format("%010d", System.currentTimeMillis() / 1000))
                .params("sort", "desc")
                .params("page", (currentPage + 1) + "")
                .params("key", "b3ae809df33f410ba94d3f72f4f2b5f4")
                .params("showapi_sign", "953a234482924251becfef4eafd4a8eb")
                .execute(new JsonCallback<List<XiaohuaModel>>() {
                    @Override
                    public void onSuccess(Response<List<XiaohuaModel>> response) {
                        for(XiaohuaModel m : response.body()){
                            mList.add(m.getContent());
                        }
                        mAdapter.notifyDataSetChanged();
                        databinding.pb.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Response<List<XiaohuaModel>> response) {
                        super.onError(response);
                        databinding.pb.setVisibility(View.GONE);
                    }
                });
    }

    private void initListener() {
        mItemTouchHelperCallback.setOnSlideListener(new OnSlideListener() {
            @Override
            public void onSliding(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                if (direction == ItemConfig.SLIDING_LEFT) {
                } else if (direction == ItemConfig.SLIDING_RIGHT) {
                }
            }

            @Override
            public void onSlided(RecyclerView.ViewHolder viewHolder, Object o, int direction) {
                if (direction == ItemConfig.SLIDED_LEFT) {
                    databinding.smileView.setDisLike((int) (Math.random()*500));
                    databinding.smileView.disLikeAnimation();
                } else if (direction == ItemConfig.SLIDED_RIGHT) {
                    databinding.smileView.setLike((int) (Math.random()*500));
                    databinding.smileView.likeAnimation();
                }
                int position = viewHolder.getAdapterPosition();
                Log.e(TAG, "onSlided--position:" + position);
            }

            @Override
            public void onClear() {
                currentPage++;
                query();
            }
        });
    }


    /**
     * 适配器
     */
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_slide, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tvContent.setText(mList.get(position));
            holder.bg.setBackgroundColor(Color.parseColor(getRandColor()));
        }

        /**
         * 获取十六进制的颜色代码.例如  "#5A6677"
         * 分别取R、G、B的随机值，然后加起来即可
         *
         * @return String
         */
        String getRandColor() {
            String R, G, B;
            Random random = new Random();
            R = Integer.toHexString(random.nextInt(256)).toUpperCase();
            G = Integer.toHexString(random.nextInt(256)).toUpperCase();
            B = Integer.toHexString(random.nextInt(256)).toUpperCase();

            R = R.length() == 1 ? "0" + R : R;
            G = G.length() == 1 ? "0" + G : G;
            B = B.length() == 1 ? "0" + B : B;

            return "#"+R + G + B;
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvContent;
            RelativeLayout bg;
            public ViewHolder(View itemView) {
                super(itemView);
                tvContent = itemView.findViewById(R.id.tv_content);
                bg = itemView.findViewById(R.id.bg);
            }
        }
    }
}
