package com.MobileApps.WeatherApp.dto;

import lombok.Data;

@Data
public class FavouriteLocationRequestDTO {
    private String cityName;
    private Double latitude;
    private Double longitude;
}

