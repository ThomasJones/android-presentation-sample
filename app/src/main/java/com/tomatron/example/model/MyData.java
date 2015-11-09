package com.tomatron.example.model;

import com.google.gson.annotations.SerializedName;

/**
 * A simple model returned from my api.
 */
public class MyData {

    @SerializedName("id")
    private long mId;

    @SerializedName("data")
    private String mData;

    @SerializedName("version")
    private int mVersion;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getData() {
        return mData;
    }

    public void setData(String data) {
        mData = data;
    }

    public int getVersion() {
        return mVersion;
    }

    public void setVersion(int version) {
        mVersion = version;
    }
}

