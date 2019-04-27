package com.android.tuan.ui;

import android.databinding.ViewDataBinding;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.mj.ui.UIActivity;
import com.android.tuan.R;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.joanzapata.android.QuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.List;

import static com.android.mj.cons.Cons.PAGE_COUNT;

/**
 * author: Rea.X
 * date: 2017/12/13.
 */

public abstract class RefreshBaseActivity<T extends ViewDataBinding, D extends AVObject> extends RefreshActivity<T, D> implements OnRefreshLoadmoreListener, AdapterView.OnItemClickListener {


    protected AVQuery<D> avQuery;


    protected void refreshQuery(AVQuery avQuery) {
        this.avQuery = avQuery;
        avQuery.skip(currentPage * PAGE_COUNT);
        avQuery.findInBackground(callback);
    }

    private FindCallback<D> callback = new FindCallback<D>() {
        @Override
        public void done(List<D> list, AVException e) {
            if (isRefresh) {
                adapter.clear();
            }
            if (list != null)
                adapter.addAll(list);
            smartRefreshLayout.finishLoadmore();
            smartRefreshLayout.finishRefresh();
            refreshView();
            dismissProgress();
        }
    };


    @Override
    protected void query() {
        avQuery = AVQuery.getQuery(getQueryClass());
        avQuery = getAvQuery(avQuery);
        avQuery.skip(currentPage * PAGE_COUNT);
        avQuery.findInBackground(callback);
    }

    protected abstract Class<D> getQueryClass();

    protected abstract AVQuery getAvQuery(AVQuery avQuery);


}
