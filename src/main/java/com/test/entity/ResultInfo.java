package com.test.entity;

import java.io.Serializable;

/**
 * @author zzh
 * @create
 * @desc 请求结果
 */
public class ResultInfo implements Serializable {

    private String returnCode; //返回码
    private String returnMessage; //返回信息
    private String rowCount; //查询行数
    private String colCount; //查询列数
    private String requestParams; //请求参数

    private String requestTime; //请求时间
    private String responseTime; //响应时间

    private String takeTime; //查询所用时间
    private String fieldNames; //返回项目名称
    private String fieldUnits; //返回的项目单位

    private Object DS;

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

    public Object getDS() {
        return DS;
    }

    public void setDS(Object DS) {
        this.DS = DS;
    }
}
