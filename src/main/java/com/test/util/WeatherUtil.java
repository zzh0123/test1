package com.test.util;


public class WeatherUtil {

    public static String dealWeatherLiveValue(String value){
        if (value != null && !value.isEmpty()){
            if ("999999".equals(value)){
                return "--";
            } else {
                return value;
            }
        } else {
            return "--";
        }
    }

    public static String dealWeatherValue(String value){
        if (value != null && !value.isEmpty()){
            return (Integer.parseInt(value) + 8 + "");
        } else {
            return "";
        }
    }

    public static String getWeatherString(String WEP_Now){
        int weatherCode = Integer.parseInt(WEP_Now);
        System.out.println("--weatherCode--" + weatherCode);
        if (weatherCode == 0){
            return "晴";
        } else if (weatherCode == 1){
            return "少云";
        } else if (weatherCode == 2){
            return "多云";
        } else if (weatherCode == 3){
            return "阴";
        } else if (weatherCode == 4){
            return "有烟尘";
        } else if (weatherCode == 5){
            return "霾";
        } else if (weatherCode >= 6 && weatherCode <=8){
            return "有浮尘或扬沙";
        } else if (weatherCode == 9){
            return "沙尘暴";
        } else if (weatherCode >= 10 && weatherCode <=12){
            return "有轻雾";
        } else if (weatherCode == 13){
            return "闪电";
        } else if (weatherCode == 14){
            return "视区内有降水但不及地";
        } else if (weatherCode == 15){
            return "视区内有降水及地，5km以外";
        } else if (weatherCode == 16){
            return "视区内有降水，本站无降水";
        } else if (weatherCode == 17){
            return "雷暴，观测时测站无降水";
        } else if (weatherCode == 18){
            return "飑";
        } else if (weatherCode == 19){
            return "龙卷";
        } else if (weatherCode == 20){
            return "毛毛雨，非阵性";
        } else if (weatherCode == 21){
            return "雨，非阵性";
        } else if (weatherCode == 22){
            return "雪、米雪或冰粒";
        } else if (weatherCode == 23){
            return "非阵性雨夹雪或雨夹冰粒";
        } else if (weatherCode == 24){
            return "非阵性毛毛雨或雨，并有雨凇结成";
        } else if (weatherCode == 25){
            return "阵雨";
        } else if (weatherCode == 26){
            return "阵雪，或阵雨夹雪";
        } else if (weatherCode == 27){
            return "阵雹，或阵雨夹雹";
        } else if (weatherCode == 28){
            return "雾或冰雾";
        } else if (weatherCode == 29){
            return "雷暴（有或无降水）";
        } else if (weatherCode >= 30 && weatherCode <= 32){
            return "低或中强度尘暴或沙暴";
        } else if (weatherCode >= 33 && weatherCode <= 35){
            return "强尘暴或沙暴";
        } else if (weatherCode >= 36 && weatherCode <= 39){
            return "吹雪";
        } else if (weatherCode >= 40 && weatherCode <= 49){
            return "有雾或冰雾";
        } else if (weatherCode >= 50 && weatherCode <= 59){
            return "毛毛雨";
        } else if (weatherCode >= 60 && weatherCode <= 69){
            return "雨";
        } else if (weatherCode >= 70 && weatherCode <= 79){
            return "降雪";
        } else if (weatherCode >= 80 && weatherCode <= 84){
            return "阵雨";
        } else if (weatherCode >= 85 && weatherCode <= 86){
            return "阵雪";
        } else if (weatherCode >= 87 && weatherCode <= 89){
            return "阵冰雹";
        } else if (weatherCode >= 87 && weatherCode <= 90){
            return "阵冰雹";
        } else if (weatherCode == 91){
            return "小雨";
        } else if (weatherCode == 92){
            return "中雨或大雨";
        } else if (weatherCode == 93){
            return "小雪，雨夹雪或雹";
        } else if (weatherCode == 94){
            return "大雪，雨夹雪或雹";
        } else if (weatherCode == 95){
            return "小或中雷暴，无雹但伴有雨夹/或雪";
        } else if (weatherCode == 96){
            return "小或中雷暴，有雹";
        } else if (weatherCode == 97){
            return "强雷暴，无雹，但伴有雨夹/或雪";
        } else if (weatherCode == 98){
            return "有雷暴并伴有尘暴或沙暴";
        } else if (weatherCode == 99){
            return "有强雷暴，并伴有雹";
        } else {
            return "";
        }
    }
}
