
package com.vinid.myfirstproject.sunshine.models;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Main {

    @SerializedName("feels_like")
    private Double mFeelsLike;
    @SerializedName("grnd_level")
    private Long mGrndLevel;
    @SerializedName("humidity")
    private Long mHumidity;
    @SerializedName("pressure")
    private Long mPressure;
    @SerializedName("sea_level")
    private Long mSeaLevel;
    @SerializedName("temp")
    private Double mTemp;
    @SerializedName("temp_kf")
    private Double mTempKf;
    @SerializedName("temp_max")
    private Double mTempMax;
    @SerializedName("temp_min")
    private Double mTempMin;

    public Double getFeelsLike() {
        return mFeelsLike;
    }

    public void setFeelsLike(Double feelsLike) {
        mFeelsLike = feelsLike;
    }

    public Long getGrndLevel() {
        return mGrndLevel;
    }

    public void setGrndLevel(Long grndLevel) {
        mGrndLevel = grndLevel;
    }

    public Long getHumidity() {
        return mHumidity;
    }

    public void setHumidity(Long humidity) {
        mHumidity = humidity;
    }

    public Long getPressure() {
        return mPressure;
    }

    public void setPressure(Long pressure) {
        mPressure = pressure;
    }

    public Long getSeaLevel() {
        return mSeaLevel;
    }

    public void setSeaLevel(Long seaLevel) {
        mSeaLevel = seaLevel;
    }

    public Double getTemp() {
        return mTemp;
    }

    public void setTemp(Double temp) {
        mTemp = temp;
    }

    public Double getTempKf() {
        return mTempKf;
    }

    public void setTempKf(Double tempKf) {
        mTempKf = tempKf;
    }

    public Double getTempMax() {
        return mTempMax;
    }

    public void setTempMax(Double tempMax) {
        mTempMax = tempMax;
    }

    public Double getTempMin() {
        return mTempMin;
    }

    public void setTempMin(Double tempMin) {
        mTempMin = tempMin;
    }

}
