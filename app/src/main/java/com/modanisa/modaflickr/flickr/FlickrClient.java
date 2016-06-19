package com.modanisa.modaflickr.flickr;

import com.modanisa.flickrapiclient.FlickrApiClient;

/**
 * Created by canavar on 6/19/2016.
 */
public class FlickrClient {
    private static final String FLICKR_API_KEY = "cd47756f4d75298318da797bec9dff86";
    private static FlickrApiClient FLICKR_CLIENT;

    static {
        setupRestClient();
    }

    private FlickrClient() {
    }

    public static FlickrApiClient get() {
        return FLICKR_CLIENT;
    }

    private static void setupRestClient() {
        FLICKR_CLIENT = new FlickrApiClient(FLICKR_API_KEY);
    }
}
