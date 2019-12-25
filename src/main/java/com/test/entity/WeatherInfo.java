package com.test.entity;

import java.io.Serializable;

public class WeatherInfo implements Serializable {

    private String weatherId; // 实时天气id nationalWeatherStationCode + times,
    private String nationalWeatherStationCode; // "12",
    private String times; // 时间,
    private String Year; // 年
    private String Mon; // 月
    private String Day; // 日
    private String Hour; // 时
    private String WEP_Now; // 天气编码

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public String getNationalWeatherStationCode() {
        return nationalWeatherStationCode;
    }

    public void setNationalWeatherStationCode(String nationalWeatherStationCode) {
        this.nationalWeatherStationCode = nationalWeatherStationCode;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getMon() {
        return Mon;
    }

    public void setMon(String mon) {
        Mon = mon;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getHour() {
        return Hour;
    }

    public void setHour(String hour) {
        Hour = hour;
    }

    public String getWEP_Now() {
        return WEP_Now;
    }

    public void setWEP_Now(String WEP_Now) {
        this.WEP_Now = WEP_Now;
    }
}
