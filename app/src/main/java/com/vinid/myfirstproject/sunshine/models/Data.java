
package com.vinid.myfirstproject.sunshine.models;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Data {

    @SerializedName("city")
    private City mCity;
    @SerializedName("cnt")
    private Long mCnt;
    @SerializedName("cod")
    private String mCod;
    @SerializedName("list")
    private java.util.List<com.vinid.myfirstproject.sunshine.models.List> mList;
    @SerializedName("message")
    private Long mMessage;

    public City getCity() {
        return mCity;
    }

    public void setCity(City city) {
        mCity = city;
    }

    public Long getCnt() {
        return mCnt;
    }

    public void setCnt(Long cnt) {
        mCnt = cnt;
    }

    public String getCod() {
        return mCod;
    }

    public void setCod(String cod) {
        mCod = cod;
    }

    public java.util.List<com.vinid.myfirstproject.sunshine.models.List> getList() {
        return mList;
    }

    public void setList(java.util.List<com.vinid.myfirstproject.sunshine.models.List> list) {
        mList = list;
    }

    public Long getMessage() {
        return mMessage;
    }

    public void setMessage(Long message) {
        mMessage = message;
    }

}
