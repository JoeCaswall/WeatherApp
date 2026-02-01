package com.MobileApps.WeatherApp.dto;

public record LocationDTO(
        String cityName,
        String stateCode,
        String countryCode,
        String countryFull,
        double latitude,
        double longitude
) {}

