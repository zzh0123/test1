package com.test.mapper;

import com.test.entity.WeatherInfo;
import com.test.entity.WeatherLiveInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface WeatherInfoMapper{

    int insertWeatherLive(WeatherLiveInfo weatherLiveInfo);

    int updateWeatherLive(WeatherLiveInfo weatherLiveInfo);

    int deleteWeatherLive(String times);

    int insertWeather(WeatherInfo weatherInfo);

    int deleteWeather(String times);
}