package com.test.entity;

import java.io.Serializable;

public class WeatherLiveInfo implements Serializable {
    private String weatherLiveId; // 实时天气id stationCode + times,
    private String weatherStationCode; // "0",
    private String times; // 时间

    private String TEM; //温度/气温
    private String RHU; //相对湿度
    private String GST; //地面温度
    private String WIN_S_Max; //最大风速
    private String WIN_D_Gust_Max; //最大阵风风向
    private String TEM_Max; //最高气温
    private String TEM_Min; //最低气温
    private String V13013; //积雪深度计算值
    private String PRE_1h; //过去1小时降水量
    private String VIS_HOR_1MI; //1分钟平均水平能见度

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

    public String getTEM() {
        return TEM;
    }

    public void setTEM(String TEM) {
        this.TEM = TEM;
    }

    public String getRHU() {
        return RHU;
    }

    public void setRHU(String RHU) {
        this.RHU = RHU;
    }

    public String getGST() {
        return GST;
    }

    public void setGST(String GST) {
        this.GST = GST;
    }

    public String getWIN_S_Max() {
        return WIN_S_Max;
    }

    public void setWIN_S_Max(String WIN_S_Max) {
        this.WIN_S_Max = WIN_S_Max;
    }

    public String getWIN_D_Gust_Max() {
        return WIN_D_Gust_Max;
    }

    public void setWIN_D_Gust_Max(String WIN_D_Gust_Max) {
        this.WIN_D_Gust_Max = WIN_D_Gust_Max;
    }

    public String getTEM_Max() {
        return TEM_Max;
    }

    public void setTEM_Max(String TEM_Max) {
        this.TEM_Max = TEM_Max;
    }

    public String getTEM_Min() {
        return TEM_Min;
    }

    public void setTEM_Min(String TEM_Min) {
        this.TEM_Min = TEM_Min;
    }

    public String getV13013() {
        return V13013;
    }

    public void setV13013(String v13013) {
        V13013 = v13013;
    }

    public String getPRE_1h() {
        return PRE_1h;
    }

    public void setPRE_1h(String PRE_1h) {
        this.PRE_1h = PRE_1h;
    }

    public String getVIS_HOR_1MI() {
        return VIS_HOR_1MI;
    }

    public void setVIS_HOR_1MI(String VIS_HOR_1MI) {
        this.VIS_HOR_1MI = VIS_HOR_1MI;
    }
}
