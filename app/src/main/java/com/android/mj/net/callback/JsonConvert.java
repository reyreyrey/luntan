package com.android.mj.net.callback;

import com.lzy.okgo.convert.Converter;

import org.json.JSONObject;

import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;


public class JsonConvert<T> implements Converter<T> {

    private Type type;

    private JsonConvert() {
    }

    public JsonConvert(Type type) {
        this.type = type;
    }


    @Override
    public T convertResponse(Response response) throws Throwable {
        return parseParameterizedType(response, type);
    }

    private T parseParameterizedType(Response response, Type type) throws Exception {
        if (type == null) return null;
        ResponseBody body = response.body();
        if (body == null) return null;

        JSONObject jsonObject = new JSONObject(body.string());
        int error_code = jsonObject.getInt("error_code");
        if (error_code != 0) {
            String message = jsonObject.getString("reason");
            throw new NetException(error_code, message);
        }
        return Convert.fromJson(jsonObject.getString("result"), type);
    }
}
