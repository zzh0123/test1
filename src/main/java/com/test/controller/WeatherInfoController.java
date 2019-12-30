package com.test.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.test.entity.ResultInfo;
import com.test.entity.WeatherInfo;
import com.test.entity.WeatherLiveInfo;
import com.test.service.WeatherInfoService;
import com.test.util.DateUtil;
import com.test.util.WeatherUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    // logback
    private final static Logger logger = LoggerFactory.getLogger(WeatherInfoController.class);

    @Autowired
    private WeatherInfoService service;

//    @Scheduled(cron = "0 */5 * * * ?") // 每隔5分执行一次
    @Scheduled(cron = "0 */5 * * * ?") // 每隔5分执行一次
    @GetMapping("/insertWeatherLive")
    public void insertWeatherLive() { // @RequestBody WeatherInfo weatherInfo
        String times = DateUtil.getStringAllDate5Min();

//        System.out.println("-insertWeatherLive-times--" + times);
        logger.info("-insertWeatherLive-times--" + times);
        queryWeahterLiveAndInsertThread("C4082", times); // 无数据
        queryWeahterLiveAndInsertThread("C3241", times);
        queryWeahterLiveAndInsertThread("C3231", times);

        queryWeahterLiveAndInsertThread("C3035", times);
        queryWeahterLiveAndInsertThread("C3246", times);
        queryWeahterLiveAndInsertThread("C3202", times); // 无数据

        queryWeahterLiveAndInsertThread("C3025", times); // 无数据
        queryWeahterLiveAndInsertThread("C3289", times); // 无数据
        queryWeahterLiveAndInsertThread("C3286", times); // 无数据
        queryWeahterLiveAndInsertThread("C3287", times); // 无数据

        queryWeahterLiveAndInsertThread("C3294", times);
        queryWeahterLiveAndInsertThread("C3208", times);
        queryWeahterLiveAndInsertThread("C3059", times);
        queryWeahterLiveAndInsertThread("C3023", times);

        queryWeahterLiveAndInsertThread("C3020", times);
        queryWeahterLiveAndInsertThread("C3161", times);
        queryWeahterLiveAndInsertThread("C3200", times); // 无数据

    }

    /*
     * 查询天气实时实况并插入数据库，5分钟更新一次数据
     */
    public void queryWeahterLiveAndInsertThread(String stationCode, String times){
        new Thread(new Runnable() {
            @Override
            public void run() {
                queryWeahterLiveAndInsert(stationCode, times);
            }
        }).start();
    }
    public void queryWeahterLiveAndInsert(String stationCode, String times) { // C3208  20191212095000  20191219050000
//        System.out.println("--stationCode--" + stationCode);
//        String times = DateUtil.getStringAllDate();

        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://10.62.89.55/cimiss-web/api?" +
                "userId=BEHT_BFDS_ordosqxtfw" +
                "&pwd=ordosqxtfw123" +
                "&interfaceId=getSurfEleByTimeAndStaID" +
                "&dataCode=SURF_CHN_MAIN_MIN" +
                "&elements=TEM,RHU,GST,WIN_S_Max,WIN_D_Gust_Max,TEM_Max,TEM_Min,V13013,PRE_1h,VIS_HOR_1MI" +
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

    private void insertWeahterLive(String weatherStationCode, String times, String strbody) {
//        Result result= JSONObject.parseObject(strbody, Result.class);
        ResultInfo resultInfo = JSON.parseObject(strbody, ResultInfo.class);
        String returnCode = resultInfo.getReturnCode();
        if (returnCode != null && "0".equals(returnCode)){
            JSONObject jsonObject = JSONObject.parseObject(strbody);
            String fieldUnits = jsonObject.getString("fieldUnits");
            String[] units = fieldUnits.split(" ");
//        logger.info("--weatherStationCode--" + weatherStationCode + "--units--" + JSON.toJSONString(units));
//        System.out.println("--fieldUnits--" + fieldUnits);

            JSONArray jsonArray = (JSONArray) jsonObject.getJSONArray("DS");
            String jsonArrayString = JSONObject.toJSONString(jsonArray); //将array数组转换成字符串
            List<WeatherLiveInfo> weatherLiveInfoList = JSONObject.parseArray(jsonArrayString, WeatherLiveInfo.class); //把字符串转换成集合
            if (weatherLiveInfoList != null && weatherLiveInfoList.size() > 0) {
                WeatherLiveInfo weatherLiveInfo = weatherLiveInfoList.get(0);
                weatherLiveInfo.setWeatherLiveId(weatherStationCode + times);
                weatherLiveInfo.setTimes(times);
                weatherLiveInfo.setWeatherStationCode(weatherStationCode);

                // 添加单位
                weatherLiveInfo.setTEM(WeatherUtil.dealWeatherLiveValue(weatherLiveInfo.getTEM()) + units[0]);
                weatherLiveInfo.setRHU(WeatherUtil.dealWeatherLiveValue(weatherLiveInfo.getRHU()) + units[1]);
                weatherLiveInfo.setGST(WeatherUtil.dealWeatherLiveValue(weatherLiveInfo.getGST()) + units[2]);
                weatherLiveInfo.setWIN_S_Max(WeatherUtil.dealWeatherLiveValue(weatherLiveInfo.getWIN_S_Max()) + units[3]);
                weatherLiveInfo.setWIN_D_Gust_Max(WeatherUtil.dealWeatherLiveValue(weatherLiveInfo.getWIN_D_Gust_Max()) + units[4]);
                weatherLiveInfo.setTEM_Max(WeatherUtil.dealWeatherLiveValue(weatherLiveInfo.getTEM_Max()) + units[5]);
                weatherLiveInfo.setTEM_Min(WeatherUtil.dealWeatherLiveValue(weatherLiveInfo.getTEM_Min()) + units[6]);
                weatherLiveInfo.setV13013(WeatherUtil.dealWeatherLiveValue(weatherLiveInfo.getV13013()));
                weatherLiveInfo.setPRE_1h(WeatherUtil.dealWeatherLiveValue(weatherLiveInfo.getPRE_1h()) + units[8]);
                weatherLiveInfo.setVIS_HOR_1MI(WeatherUtil.dealWeatherLiveValue(weatherLiveInfo.getVIS_HOR_1MI()) + units[9]);
//            System.out.println("--setDay--" + weatherLiveInfo.getDay());
//            System.out.println("--weatherInfo--" + JSON.toJSONString(weatherLiveInfo));
                Integer count = null;
                count = service.insertWeatherLive(weatherLiveInfo);
                if (count != null) {
//                System.out.println("--插入数据成功--" + count);
                    logger.info("--插入数据成功--" + count);
                } else {
//                System.out.println("--插入数据失败--" + count);
                    logger.info("--插入数据失败--" + count);
                }

            } else {
                logger.info(weatherStationCode + "--查询天气实时数据为空--");
//            System.out.println("--查询天气实时数据为空--");
            }
        } else {
            logger.info(weatherStationCode + "--查询天气实时数据为空--");
        }

    }

    //    @Scheduled(cron = "0 */2 * * * ?") // 每隔5分执行一次
    @Scheduled(cron = "0 05 0/1 * * ?") // 每隔整点运行一次
    @GetMapping("/insertWeather")
    public void insertWeather() { // @RequestBody WeatherInfo weatherInfo
        String timesInsertWeather = DateUtil.getStringAllDate1Hour();

//        System.out.println("-insertWeather-times--" + timesInsertWeather);
        logger.info("-insertWeather-times--" + timesInsertWeather);
        queryWeahterAndInsertThread("53543", timesInsertWeather); // 东胜
        queryWeahterAndInsertThread("53457", timesInsertWeather); // 达拉特
        queryWeahterAndInsertThread("53446", timesInsertWeather); // 包头市的天气实况

    }

    /*
     * 查询天气晴雨实况并插入数据库，1小时更新数据
     */
    public void queryWeahterAndInsertThread(String nationalWeatherStationCode, String times) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                queryWeahterAndInsert(nationalWeatherStationCode, times);
            }
        }).start();
    }

    private void queryWeahterAndInsert(String nationalWeatherStationCode, String times) { // 53543  20191223010000
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
        ResultInfo resultInfo = JSON.parseObject(strbody, ResultInfo.class);
        String returnCode = resultInfo.getReturnCode();
        if (returnCode != null && "0".equals(returnCode)){
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
                weatherInfo.setHour(WeatherUtil.dealWeatherValue(weatherInfo.getHour()) + units[3]);
                weatherInfo.setWEP_Now(WeatherUtil.getWeatherString(weatherInfo.getWEP_Now()));
//            System.out.println("--weatherInfo--" + JSON.toJSONString(weatherInfo));
                Integer count = null;
                count = service.insertWeather(weatherInfo);
                if (count != null) {
//                System.out.println("--插入数据成功--" + count);
                    logger.info("--插入数据成功--" + count);
                } else {
//                System.out.println("--插入数据失败--" + count);
                    logger.info("--插入数据失败--" + count);
                }

            } else {
//            System.out.println("--查询天气晴雨数据为空--");
                logger.info(nationalWeatherStationCode + "--查询天气晴雨数据为空--");
            }
        } else {
            logger.info(nationalWeatherStationCode + "--查询天气晴雨数据为空--");
        }

    }


//    @Scheduled(cron = "0 05 0/1 * * ?") // 每隔整点运行一次
    @Scheduled(cron = "0 */5 * * * ?") // 每隔5分执行一次
    @GetMapping("/deleteWeatherLive")
    public void deleteWeatherLive() { // @RequestBody WeatherInfo weatherInfo
        String timesDeleteWeatherLive = DateUtil.getStringAllDate10Min();

//        System.out.println("-deleteWeatherLive-times--" + timesDeleteWeatherLive);
        logger.info("-deleteWeatherLive-times--" + timesDeleteWeatherLive);
        deleteWeatherLiveThread(timesDeleteWeatherLive);

    }

    /*
     * 删除天气实况数据
     */
    public void deleteWeatherLiveThread(String times) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Integer count = null;
                count = service.deleteWeatherLive(times);
                if (count != null) {
//                    System.out.println("--删除数据成功--" + count);
                    logger.info("--删除数据成功--" + count);
                } else {
//                    System.out.println("--删除数据失败--" + count);
                    logger.info("--删除数据失败--" + count);
                }

            }
        }).start();
    }


//    @Scheduled(cron = "0 */5 * * * ?") // 每隔5分执行一次
    @Scheduled(cron = "0 05 0/1 * * ?") // 每隔整点运行一次
    @GetMapping("/deleteWeather")
    public void deleteWeather() { // @RequestBody WeatherInfo weatherInfo
        String timesDeleteWeather = DateUtil.getStringAllDate2Hour();

//        System.out.println("-deleteWeather-times--" + timesDeleteWeather);
        logger.info("-deleteWeather-times--" + timesDeleteWeather);
        deleteWeatherThread(timesDeleteWeather);

    }

    /*
     * 删除天气实况数据
     */
    public void deleteWeatherThread(String times) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Integer count = null;
                count = service.deleteWeather(times);
                if (count != null) {
//                    System.out.println("--删除数据成功--" + count);
                    logger.info("--删除数据成功--" + count);
                } else {
//                    System.out.println("--删除数据失败--" + count);
                    logger.info("--删除数据失败--" + count);
                }

            }
        }).start();
    }
}
