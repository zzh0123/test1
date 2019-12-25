package com.test.service;

import com.test.entity.WeatherInfo;
import com.test.entity.WeatherLiveInfo;
import com.test.mapper.WeatherInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zzh
 * @create 2019-08-18 21:55
 * @desc User服务
 */
@Service
public class WeatherInfoService {

    @Autowired
    private WeatherInfoMapper mapper;

    /**
     * 功能描述: 插入实时天气信息
     * @param: weatherInfo
     * @return: count
     * @auther: zzh
     * @date: 2019-08-18 22:34
     */
    public int insertWeatherLive(WeatherLiveInfo weatherLiveInfo){
        Integer count = null;
        count = mapper.insertWeatherLive(weatherLiveInfo);
        return count;
    }

    /**
     * 功能描述: 插入天气晴雨信息
     * @param: weatherInfo
     * @return: count
     * @auther: zzh
     * @date: 2019-08-18 22:34
     */
    public int insertWeather(WeatherInfo weatherInfo){
        Integer count = null;
        count = mapper.insertWeather(weatherInfo);
        return count;
    }

}
