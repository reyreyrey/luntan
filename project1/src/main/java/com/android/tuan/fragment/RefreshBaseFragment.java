package com.android.tuan.fragment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.mj.ui.UIActivity;
import com.android.mj.ui.UIBaseFragment;
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

public abstract class RefreshBaseFragment<T extends ViewDataBinding, D extends AVObject> extends UIBaseFragment<T> implements OnRefreshLoadmoreListener, AdapterView.OnItemClickListener {

    protected int currentPage = 0;
    protected AVQuery<D> avQuery;
    protected QuickAdapter<D> adapter;
    protected boolean isRefresh;
    protected SmartRefreshLayout smartRefreshLayout;
    protected ListView listView;
    protected TextView emptyView;


    @Override
    protected void lazyLoad() {
        if (needQueryInInit())
            smartRefreshLayout.autoRefresh();
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_refresh_listview;
    }


    @Override
    protected void init(View view) {
        listView = (ListView) view.findViewById(R.id.listview);
        emptyView = (TextView) view.findViewById(R.id.emptyView);
        smartRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);
        listView.setOnItemClickListener(this);
        smartRefreshLayout.setOnRefreshLoadmoreListener(this);
        adapter = getAdapter();
        listView.setAdapter(adapter);
        avQuery = AVQuery.getQuery(getQueryClass());
        avQuery = getAvQuery(avQuery);
        avQuery.limit(PAGE_COUNT);
    }

    protected void refreshQuery(AVQuery avQuery) {
        this.avQuery = avQuery;
        query();
    }

    private FindCallback<D> callback = new FindCallback<D>() {
        @Override
        public void done(List<D> list, AVException e) {
            if (isRefresh) {
                adapter.clear();
            }
            smartRefreshLayout.finishLoadmore();
            smartRefreshLayout.finishRefresh();
            if (list != null)
                adapter.addAll(list);
            refreshView();
        }
    };

    protected void showEmptyView() {
        listView.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }

    protected void showContent() {
        listView.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
    }

    private void refreshView() {
        if (adapter.getCount() <= 0) {
            showEmptyView();
        } else {
            showContent();
        }
    }


    protected void query() {
        avQuery.skip(currentPage * PAGE_COUNT);
        avQuery.findInBackground(callback);
    }

    protected abstract Class<D> getQueryClass();

    protected abstract AVQuery getAvQuery(AVQuery avQuery);

    protected abstract QuickAdapter<D> getAdapter();

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        isRefresh = false;
        currentPage++;
        query();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        showContent();
        isRefresh = true;
        currentPage = 0;
        query();
    }

    protected boolean needQueryInInit() {
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
