package com.MobileApps.WeatherApp.dto;

public record CityRecord(
        int cityId,
        String cityName,
        String stateCode,
        String countryCode,
        String countryFull,
        double lat,
        double lon
) {}

