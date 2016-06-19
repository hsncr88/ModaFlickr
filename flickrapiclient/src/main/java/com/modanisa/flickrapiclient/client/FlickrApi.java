package com.modanisa.flickrapiclient.client;

import com.modanisa.flickrapiclient.entity.Photos;
import com.modanisa.flickrapiclient.entity.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by canavar on 6/19/2016.
 */

public interface FlickrApi {

    @GET("services/rest/")
    Call<Result<Photos>> getPhotoListByTag(@Query("method") String method, @Query("api_key") String apiKey, @Query("tags")String  tag, @Query("per_page") int perPage, @Query("page") int page);
}
