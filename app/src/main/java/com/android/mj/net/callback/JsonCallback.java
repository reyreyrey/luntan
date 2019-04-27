package com.android.mj.net.callback;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 创建时间：2019/4/16
 * 方法编写人：Rea.X
 * 功能描述：
 */
public abstract class JsonCallback<T> implements Callback<T>{
    @Override
    public void onStart(Request<T, ? extends Request> request) {
    }

    @Override
    public void onCacheSuccess(Response<T> response) {

    }

    @Override
    public void onError(Response<T> response) {

    }

    @Override
    public void onFinish() {

    }

    @Override
    public void uploadProgress(Progress progress) {

    }

    @Override
    public void downloadProgress(Progress progress) {

    }

    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        Type genType = getClass().getGenericSuperclass();
        JsonConvert<T> convert = new JsonConvert<>(((ParameterizedType) genType).getActualTypeArguments()[0]);
        return convert.convertResponse(response);
    }
}
