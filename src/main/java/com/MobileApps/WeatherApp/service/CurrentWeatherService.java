package com.MobileApps.WeatherApp.service;

import com.MobileApps.WeatherApp.dto.CurrentWeatherDTO;
import com.MobileApps.WeatherApp.middleware.CurrentWeatherMiddleware;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CurrentWeatherService {

    private final CurrentWeatherMiddleware middleware;

    public CurrentWeatherDTO getCurrentWeather(double lat, double lon) {
        return middleware.getCurrentWeather(lat, lon);
    }

    public CurrentWeatherDTO getCurrentWeather(String cityName) {
        return middleware.getCurrentWeather(cityName);
    }
}
