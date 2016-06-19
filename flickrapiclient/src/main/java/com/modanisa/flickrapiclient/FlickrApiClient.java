package com.modanisa.flickrapiclient;

import android.support.annotation.NonNull;

import com.modanisa.flickrapiclient.client.RestClient;
import com.modanisa.flickrapiclient.entity.Photos;
import com.modanisa.flickrapiclient.entity.Result;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by canavar on 6/18/2016.
 */
public class FlickrApiClient {

    private static String API_KEY = null;

    /**
     * @param apiKey  Flickr Api Key
     * @return Flickr Api Client to perform flickr requests
     */
    public FlickrApiClient(@NonNull String apiKey) {
        if(apiKey == null)
        {
            throw new NullPointerException("apiKey cannot be null.");
        }

        API_KEY = apiKey;
    }

    /**
     * This method is used to get photo list that is filtered by given tag.
     * @param tag  given tag to filter photo list
     * @param perPage  paging size
     * @param page  page number
     * @param callback  Response callback
     */
    public void getPhotoListByTag(@NonNull String tag, int perPage, int page,@NonNull Callback<Result<Photos>> callback)
    {
        Call<Result<Photos>> call =  RestClient.get().getPhotoListByTag("flickr.photos.search", API_KEY, tag, perPage, page);
        if(callback != null)
            call.enqueue(callback);
    }
}
