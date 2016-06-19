package com.modanisa.flickrapiclient.client;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by canavar on 6/19/2016.
 */
public class RestClient {
    private static FlickrApi REST_CLIENT;
    private static final String ROOT_URL = "https://api.flickr.com/";

    static {
        setupRestClient();
    }

    private RestClient() {
    }

    public static FlickrApi get() {
        return REST_CLIENT;
    }

    private static void setupRestClient() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("format","json").addQueryParameter("nojsoncallback","1") // add format json query parameter on every request
                        .build();
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        }).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        REST_CLIENT = retrofit.create(FlickrApi.class);
    }
}
