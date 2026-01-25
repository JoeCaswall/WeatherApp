package com.MobileApps.WeatherApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CurrentWeatherDTO {
    private double temp;
    private double feelsLikeTemp;
    private String windDirection;
    private double windSpeedKmh;
    private WeatherbitCurrentConditions conditions;
    private int airQuality;
    private String sunriseTime;
    private String sunsetTime;
}
