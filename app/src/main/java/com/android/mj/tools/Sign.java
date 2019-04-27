package com.android.mj.tools;

import android.text.TextUtils;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Sign {
    private static Map<String, String> sign(Map<String, String> params) {
        int i = 0;
        if (params.containsKey("sign")) {
            params.remove("sign");
        }
        String[] strs = new String[params.size()];
        for (Map.Entry<String, String> map : params.entrySet()) {
            String key = map.getKey();
            strs[i] = key;
            i++;
        }
        Arrays.sort(strs);
        StringBuilder sb = new StringBuilder();
        int position = 0;
        for (String key : strs) {
            position++;
            sb.append(params.get(key));
        }
        sb.append("1JYDDH9o3UZ2wH713mXCjOhR0WPYOn4UERYgTaIEirsRQWJpzrKauVIYUFBA5xqRwt2zL9Vg");
        String sign = MD5.md5(sb.toString()).toUpperCase();
        params.put("sign", sign);
        return params;
    }


    public static Map<String, String> getParams(Map<String, String> params) {
        if (params == null)
            params = new HashMap<>();
        String app_key = "CD7313E678CF8751F430CEC73A9365C6";
        params.put("app_key", app_key);
        return sign(params);
    }
}
