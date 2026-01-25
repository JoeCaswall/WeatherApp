package com.MobileApps.WeatherApp.dto;

import lombok.Data;

import java.util.List;

@Data
public class CurrentWeatherResponse {
    private List<WeatherbitData> data;
    private List<Object> minutely;
    private int count;
}