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
     * @date:
     */
    public int insertWeatherLive(WeatherLiveInfo weatherLiveInfo){
        Integer count = null;
        count = mapper.insertWeatherLive(weatherLiveInfo);
        return count;
    }

    /**
     * 功能描述: 插入实时天气信息
     * @param: weatherLiveInfo
     * @return: count
     * @auther: zzh
     * @date:
     */
    public int updateWeatherLive(WeatherLiveInfo weatherLiveInfo){
        Integer count = null;
        count = mapper.updateWeatherLive(weatherLiveInfo);
        return count;
    }

    /**
     * 功能描述: 删除实时天气信息
     * @param: times
     * @return: count
     * @auther: zzh
     * @date:
     */
    public int deleteWeatherLive(String times){
        Integer count = null;
        count = mapper.deleteWeatherLive(times);
        return count;
    }

    /**
     * 功能描述: 插入天气晴雨信息
     * @param: weatherInfo
     * @return: count
     * @auther: zzh
     * @date:
     */
    public int insertWeather(WeatherInfo weatherInfo){
        Integer count = null;
        count = mapper.insertWeather(weatherInfo);
        return count;
    }

    /**
     * 功能描述: 删除天气晴雨信息
     * @param: times
     * @return: count
     * @auther: zzh
     * @date:
     */
    public int deleteWeather(String times){
        Integer count = null;
        count = mapper.deleteWeather(times);
        return count;
    }

}
