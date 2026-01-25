package com.MobileApps.WeatherApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class WeatherbitCurrentConditions {
    private String description;
    private String icon;
    private int code;
}
