package com.MobileApps.WeatherApp.dto;

import lombok.Data;

@Data
public class FavouriteLocationDTO {
    private Long id;
    private String cityName;
    private Double latitude;
    private Double longitude;
}
