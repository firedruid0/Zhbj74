package com.itheima.zhbj74.utils;

import android.content.Context;

/**
 *
 */
public class CacheUtils {

    //以url为key，以json为value，保存在本地

    public static void setCache(String url, String json, Context ctx){
        PrefUtils.setString(ctx, url, json);
    }

    public static String getCache(String url, Context ctx){
        return PrefUtils.getString(ctx, url, null);
    }

}
