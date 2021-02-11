package com.codepath.apps.restclienttemplate.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Media {

    public String mediaHTTPS;
    public String mediaHTTP;
    public boolean hasMedia;
    public long id;

    public Media(){}

    // JSONObject will be "entities" object
    public static Media fromJSON(JSONObject jsonObject) throws JSONException {
        Media media = new Media();
        if (jsonObject.has("media")) {
            media.hasMedia = true;
            JSONObject mediaObject = jsonObject.getJSONArray("media").getJSONObject(0);
            media.mediaHTTP = mediaObject.getString("media_url");
            media.mediaHTTPS = mediaObject.getString("media_url_https");
            media.id = mediaObject.getLong("id");
            return media;
        }
        media.hasMedia = false;
        return media;
    }
}
