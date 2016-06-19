package com.modanisa.flickrapiclient.entity;

/**
 * Created by canavar on 6/19/2016.
 */
public class Result<T> {
    private T photos = null;
    private String stat = null;
    public int code;
    public String message = null;

    public T getPhotos() {
        return photos;
    }

    public void setPhotos(T photos) {
        this.photos = photos;
    }

    public boolean isSuccess()
    {
        return "ok".equals(stat) == true ? true : false;
    }

    public String getMessage() {
        return message;
    }
}
