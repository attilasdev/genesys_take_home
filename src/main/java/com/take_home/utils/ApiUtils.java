package com.take_home.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class ApiUtils {
    private static final OkHttpClient client = new OkHttpClient();

    public static String sendGetRequest(String url) throws IOException {
        Request request =new Request.Builder()
            .url(url)
            .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
    
}
