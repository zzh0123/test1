package com.test.entity;

import java.io.Serializable;

public class WeatherResponse implements Serializable {
        private String returnCode; // "0",
        private String returnMessage; // "Query Succeed",
        private String rowCount; // "11",
        private String colCount; // "42",
        private String requestParams; // "limitcnt=30×=20191215000000&datacode=SURF_WEA_CHN_MUL_RAIL-WAY&elements=Station_Name,Province,City,Cnty,Town,NetCode,Datetime,IYMDHM,RYMDHM,UPDATE_TIME,Station_Id_d,Station_Id_C,Year,Mon,Day,Hour,Min,Lat,Lon,Alti,PRE_1h,PRE,WIN_D_Avg_2mi,WIN_S_Avg_2mi,WIN_D_Avg_10mi,WIN_S_Avg_10mi,WIN_D_S_Max,WIN_S_Max,WIN_S_Max_OTime,WIN_D_INST,WIN_S_INST,WIN_D_INST_Max,WIN_S_Inst_Max,WIN_S_INST_Max_OTime,GST,GST_Max,GST_Max_Otime,GST_Min,GST_Min_OTime,PRE_STR,WIN_S_INST_MIN,WIN_D_INST_MIN",
        private String requestTime; // "2019-12-18 09:26:13",
        private String responseTime; // "2019-12-18 09:26:13",
        private String takeTime; // "0.106",
        private String fieldNames; // "站名 省份 地市 区县 乡镇 站网信息 资料时间 入库时间 收到时间 更新时间 区站号(数字) 区站号(字符) 年 月 日 时 分 纬度 经度 测站高度 过去1小时降水量 降水量 2分钟平均风向 2分钟平均风速 10分钟平均风向 10分钟平均风速 最大风速的风向 最大风速 最大风速出现时间 瞬时风向 瞬时风速 极大风速的风向 极大风速 极大风速出现时间 地面温度 最高地面温度 最高地面温度出现时间 最低地面温度 最低地面温度出现时间 分钟雨量串 分钟内最大瞬时风速 分钟内最大瞬时风向",
        private String fieldUnits; //

        private WeatherLiveInfo DS;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public String getRowCount() {
        return rowCount;
    }

    public void setRowCount(String rowCount) {
        this.rowCount = rowCount;
    }

    public String getColCount() {
        return colCount;
    }

    public void setColCount(String colCount) {
        this.colCount = colCount;
    }

    public String getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public String getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(String takeTime) {
        this.takeTime = takeTime;
    }

    public String getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(String fieldNames) {
        this.fieldNames = fieldNames;
    }

    public String getFieldUnits() {
        return fieldUnits;
    }

    public void setFieldUnits(String fieldUnits) {
        this.fieldUnits = fieldUnits;
    }

    public WeatherLiveInfo getDS() {
        return DS;
    }

    public void setDS(WeatherLiveInfo DS) {
        this.DS = DS;
    }
}
