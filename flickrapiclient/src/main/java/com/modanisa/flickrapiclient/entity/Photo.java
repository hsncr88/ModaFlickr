package com.modanisa.flickrapiclient.entity;

/**
 * Created by canavar on 6/19/2016.
 */
public class Photo {

    public enum PhotoSize
    {
        SMALL("m"), MEDIUM("z"), LARGE("b");

        private String code;

        PhotoSize(String code)
        {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    public String id;
    public String owner;
    public String secret;
    public String server;
    public int farm;
    public String title;
    public int ispublic;
    public int isfriend;
    public int isfamily;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getFarm() {
        return farm;
    }

    public void setFarm(int farm) {
        this.farm = farm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * format https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}_[mstzb].jpg
     * @see <a href="https://www.flickr.com/services/api/misc.urls.html">https://www.flickr.com/services/api/misc.urls.html</a>
     * */
    public String getPhotoUrlString(PhotoSize size)
    {
        String photoUrl = String.format("https://farm%d.staticflickr.com/%s/%s_%s_%s.jpg", getFarm(), getServer(), getId(), getSecret(), size.getCode());
        return photoUrl;
    }
}
