package com.example.smartcity_test3.utils;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class KenUtil {

    /**
     * 无token
     * @param url
     * @return
     * @throws IOException
     */
    public static String Get(String url) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().method("GET",null).url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }

}
