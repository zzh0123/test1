package com.test.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.test.entity.WeatherInfo;
import com.test.entity.WeatherLiveInfo;
import com.test.service.WeatherInfoService;
import com.test.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class WeatherInfoController {

    @Autowired
    private WeatherInfoService service;

//    @Scheduled(cron = "0 */5 * * * ?") // 每隔5分执行一次
    @Scheduled(cron = "0 */5 * * * ?") // 每隔5分执行一次
    @GetMapping("/insertWeatherLive")
    public void insertWeatherLive() { // @RequestBody WeatherInfo weatherInfo
        String times = DateUtil.getStringAllDate5Min();

//        System.out.println("-insertWeatherLive-times--" + times);
        new Thread(new Runnable() {
            @Override
            public void run() {
                queryWeahterLiveAndInsert("C4082", times);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                queryWeahterLiveAndInsert("C3241", times);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                queryWeahterLiveAndInsert("C3231", times);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                queryWeahterLiveAndInsert("C3035", times);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                queryWeahterLiveAndInsert("C3246", times);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                queryWeahterLiveAndInsert("C3202", times);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                queryWeahterLiveAndInsert("C3025", times);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                queryWeahterLiveAndInsert("C3289", times);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                queryWeahterLiveAndInsert("C3286", times);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                queryWeahterLiveAndInsert("C3287", times);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                queryWeahterLiveAndInsert("C3294", times);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                queryWeahterLiveAndInsert("C3208", times);
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                queryWeahterLiveAndInsert("C3059", times);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                queryWeahterLiveAndInsert("C3023", times);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                queryWeahterLiveAndInsert("C3020", times);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                queryWeahterLiveAndInsert("C3161", times);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                queryWeahterLiveAndInsert("C3200", times);
            }
        }).start();

    }

    /*
     * 查询天气实时实况并插入数据库，5分钟更新一次数据
     */
    public void queryWeahterLiveAndInsert(String stationCode, String times) { // C3208  20191212095000  20191219050000
//        System.out.println("--stationCode--" + stationCode);
//        String times = DateUtil.getStringAllDate();

        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://10.62.89.55/cimiss-web/api?" +
                "userId=BEHT_BFDS_ordosqxtfw" +
                "&pwd=ordosqxtfw123" +
                "&interfaceId=getSurfEleByTimeAndStaID" +
                "&dataCode=SURF_WEA_CHN_MUL_RAIL-WAY" +
                "&elements=Year,Mon,Day,Hour,Min,PRE,WIN_D_Avg_10mi,WIN_S_Avg_10mi,WIN_S_Max,GST,GST_Max,GST_Min" +
                "&times=" + times +
                "&staIds=" + stationCode +
                "&limitCnt=30" +
                "&dataFormat=json";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String strbody = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class).getBody();
//        System.out.println("--strbody--" + strbody);
        insertWeahterLive(stationCode, times, strbody);
//        System.out.println("--结束任务--");
    }

    private void insertWeahterLive(String stationCode, String times, String strbody) {
//        Result result= JSONObject.parseObject(strbody, Result.class);
        JSONObject jsonObject = JSONObject.parseObject(strbody);
        String fieldUnits = jsonObject.getString("fieldUnits");
        String[] units = fieldUnits.split(" ");
//        System.out.println("--fieldUnits--" + fieldUnits);

        JSONArray jsonArray = (JSONArray) jsonObject.getJSONArray("DS");
        String jsonArrayString = JSONObject.toJSONString(jsonArray); //将array数组转换成字符串
        List<WeatherLiveInfo> weatherLiveInfoList = JSONObject.parseArray(jsonArrayString, WeatherLiveInfo.class); //把字符串转换成集合
        if (weatherLiveInfoList != null && weatherLiveInfoList.size() > 0) {
            WeatherLiveInfo weatherLiveInfo = weatherLiveInfoList.get(0);
            weatherLiveInfo.setWeatherLiveId(stationCode + times);
            weatherLiveInfo.setTimes(times);
            weatherLiveInfo.setWeatherStationCode(stationCode);

            // 添加单位
            weatherLiveInfo.setYear(weatherLiveInfo.getYear() + units[0]);
            weatherLiveInfo.setMon(weatherLiveInfo.getMon() + units[1]);
            weatherLiveInfo.setDay(weatherLiveInfo.getDay() + units[2]);
            weatherLiveInfo.setHour(weatherLiveInfo.getHour() + units[3]);
            weatherLiveInfo.setMin(weatherLiveInfo.getMin() + units[4]);
            weatherLiveInfo.setPRE(weatherLiveInfo.getPRE() + units[5]);
            weatherLiveInfo.setWIN_D_Avg_10mi(weatherLiveInfo.getWIN_D_Avg_10mi() + units[6]);
            weatherLiveInfo.setWIN_S_Avg_10mi(weatherLiveInfo.getWIN_S_Avg_10mi() + units[7]);
            weatherLiveInfo.setWIN_S_Max(weatherLiveInfo.getWIN_S_Max() + units[8]);
            weatherLiveInfo.setGST(weatherLiveInfo.getGST() + units[9]);
            weatherLiveInfo.setGST_Max(weatherLiveInfo.getGST_Max() + units[10]);
            weatherLiveInfo.setGST_Min(weatherLiveInfo.getGST_Min() + units[11]);
//            System.out.println("--setDay--" + weatherLiveInfo.getDay());
            System.out.println("--weatherInfo--" + JSON.toJSONString(weatherLiveInfo));
            Integer count = null;
            count = service.insertWeatherLive(weatherLiveInfo);
            if (count != null) {
                System.out.println("--插入数据成功--" + count);
            } else {
                System.out.println("--插入数据失败--" + count);
            }

        } else {
            System.out.println("--查询天气实时数据为空--");
        }
    }

    @Scheduled(cron = "0 05 0/1 * * ?") // 每隔整点运行一次
//    @Scheduled(cron = "0 */2 * * * ?") // 每隔5分执行一次
    @GetMapping("/insertWeather")
    public void insertWeather() { // @RequestBody WeatherInfo weatherInfo
        String times = DateUtil.getStringAllDate1Hour();

        System.out.println("-insertWeather-times--" + times);
        new Thread(new Runnable() {
            @Override
            public void run() {
                queryWeahterAndInsert("53543", times);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                queryWeahterAndInsert("53457", times);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                queryWeahterAndInsert("53446", times);
            }
        }).start();

    }

    /*
     * 查询天气晴雨实况并插入数据库，1小时更新数据
     */
    public void queryWeahterAndInsert(String nationalWeatherStationCode, String times) { // 53543  20191223010000
//        System.out.println("--nationalWeatherStationCode--" + nationalWeatherStationCode);
//        String times = DateUtil.getStringAllDate();

        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://10.62.89.55/cimiss-web/api?" +
                "userId=BEHT_BFDS_ordosqxtfw" +
                "&pwd=ordosqxtfw123" +
                "&interfaceId=getSurfEleByTimeAndStaID" +
                "&dataCode=SURF_CHN_MUL_HOR_N" +
                "&elements=Year,Mon,Day,Hour,WEP_Now" +
                "&times=" + times +
                "&staIds=" + nationalWeatherStationCode +
                "&limitCnt=30" +
                "&dataFormat=json";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String strbody = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class).getBody();
//        System.out.println("--strbody--" + strbody);
        insertWeahter(nationalWeatherStationCode, times, strbody);
//        System.out.println("--结束任务--");
    }

    private void insertWeahter(String nationalWeatherStationCode, String times, String strbody) {
//        Result result= JSONObject.parseObject(strbody, Result.class);
        JSONObject jsonObject = JSONObject.parseObject(strbody);
        String fieldUnits = jsonObject.getString("fieldUnits");
        String[] units = fieldUnits.split(" ");
        JSONArray jsonArray = (JSONArray) jsonObject.getJSONArray("DS");
        String jsonArrayString = JSONObject.toJSONString(jsonArray); //将array数组转换成字符串
        List<WeatherInfo> weatherInfoList = JSONObject.parseArray(jsonArrayString, WeatherInfo.class); //把字符串转换成集合
        if (weatherInfoList != null && weatherInfoList.size() > 0) {
            WeatherInfo weatherInfo = weatherInfoList.get(0);
            weatherInfo.setWeatherId(nationalWeatherStationCode + times);
            weatherInfo.setTimes(times);
            weatherInfo.setNationalWeatherStationCode(nationalWeatherStationCode);
            weatherInfo.setYear(weatherInfo.getYear() + units[0]);
            weatherInfo.setMon(weatherInfo.getMon() + units[1]);
            weatherInfo.setDay(weatherInfo.getDay() + units[2]);
            weatherInfo.setHour(weatherInfo.getHour() + units[3]);
            weatherInfo.setWEP_Now(getWeatherString(weatherInfo.getWEP_Now()));
            System.out.println("--weatherInfo--" + JSON.toJSONString(weatherInfo));
            Integer count = null;
            count = service.insertWeather(weatherInfo);
            if (count != null) {
                System.out.println("--插入数据成功--" + count);
            } else {
                System.out.println("--插入数据失败--" + count);
            }

        } else {
            System.out.println("--查询天气晴雨数据为空--");
        }
    }

    private String getWeatherString(String WEP_Now){
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
