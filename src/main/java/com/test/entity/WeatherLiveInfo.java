package com.test.entity;

import java.io.Serializable;

public class WeatherLiveInfo implements Serializable {
    private String weatherLiveId; // 实时天气id stationCode + times,
    private String weatherStationCode; // "0",
    private String times; // 时间
    private String Year; // 年
    private String Mon; // 月,
    private String Day; // 日
    private String Hour; // 时
    private String Min; // 分

    private String PRE; // 降水量
    private String WIN_D_Avg_10mi; // 10分钟平均风向
    private String WIN_S_Avg_10mi; // 10分钟平均风速
    private String WIN_S_Max; // 最大风速
    private String GST; // 地面温度
    private String GST_Max; // 最高地面温度
    private String GST_Min; // 最低地面温度

    public String getWeatherLiveId() {
        return weatherLiveId;
    }

    public void setWeatherLiveId(String weatherLiveId) {
        this.weatherLiveId = weatherLiveId;
    }

    public String getWeatherStationCode() {
        return weatherStationCode;
    }

    public void setWeatherStationCode(String weatherStationCode) {
        this.weatherStationCode = weatherStationCode;
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

    public String getMin() {
        return Min;
    }

    public void setMin(String min) {
        Min = min;
    }

    public String getPRE() {
        return PRE;
    }

    public void setPRE(String PRE) {
        this.PRE = PRE;
    }

    public String getWIN_D_Avg_10mi() {
        return WIN_D_Avg_10mi;
    }

    public void setWIN_D_Avg_10mi(String WIN_D_Avg_10mi) {
        this.WIN_D_Avg_10mi = WIN_D_Avg_10mi;
    }

    public String getWIN_S_Avg_10mi() {
        return WIN_S_Avg_10mi;
    }

    public void setWIN_S_Avg_10mi(String WIN_S_Avg_10mi) {
        this.WIN_S_Avg_10mi = WIN_S_Avg_10mi;
    }

    public String getWIN_S_Max() {
        return WIN_S_Max;
    }

    public void setWIN_S_Max(String WIN_S_Max) {
        this.WIN_S_Max = WIN_S_Max;
    }

    public String getGST() {
        return GST;
    }

    public void setGST(String GST) {
        this.GST = GST;
    }

    public String getGST_Max() {
        return GST_Max;
    }

    public void setGST_Max(String GST_Max) {
        this.GST_Max = GST_Max;
    }

    public String getGST_Min() {
        return GST_Min;
    }

    public void setGST_Min(String GST_Min) {
        this.GST_Min = GST_Min;
    }
}
