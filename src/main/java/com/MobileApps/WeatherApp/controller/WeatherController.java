package com.MobileApps.WeatherApp.controller;

import com.MobileApps.WeatherApp.service.CurrentWeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MobileApps.WeatherApp.dto.CurrentWeatherDTO;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final CurrentWeatherService weatherService;

    public WeatherController(CurrentWeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/current/coordinates")
    public CurrentWeatherDTO getCurrentWeatherByCoordinates(
            @RequestParam double lat,
            @RequestParam double lon
    ) {
        return weatherService.getCurrentWeather(lat, lon);
    }

    @GetMapping("/current/city")
    public CurrentWeatherDTO getCurrentWeatherByCity(
            @RequestParam String city
    ) {
        return weatherService.getCurrentWeather(city);
    }
}

