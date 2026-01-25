package com.MobileApps.WeatherApp.middleware;

import com.MobileApps.WeatherApp.dto.CurrentWeatherDTO;
import com.MobileApps.WeatherApp.dto.WeatherbitCurrentConditions;
import com.MobileApps.WeatherApp.dto.WeatherbitData;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MobileApps.WeatherApp.client.WeatherbitCurrentWeatherClient;
import com.MobileApps.WeatherApp.dto.CurrentWeatherResponse;
import org.springframework.beans.factory.annotation.Value;

//@AllArgsConstructor
@Service
public class CurrentWeatherMiddleware {
    private final WeatherbitCurrentWeatherClient client;
    private final String apiKey;

    @Autowired
    public CurrentWeatherMiddleware(WeatherbitCurrentWeatherClient client, @Value("${weatherbit.api.key}") String apiKey) {
        this.client = client;
        this.apiKey = apiKey;
    }

    public CurrentWeatherDTO getCurrentWeather(double lat, double lon) {
        System.out.println("Calling Weatherbit with key = " + apiKey);
        CurrentWeatherResponse response = client.getCurrentWeather(lat, lon, apiKey);
        WeatherbitData data = response.getData().get(0);
        // Convert m/s → km/h
        double windSpeedKmh = data.getWind_spd() * 3.6;
        WeatherbitCurrentConditions conditions = new WeatherbitCurrentConditions(
                data.getWeather().getDescription(),
                data.getWeather().getIcon(),
                data.getWeather().getCode()
        );
        return new CurrentWeatherDTO(
                data.getTemp(),
                data.getApp_temp(),
                data.getWind_cdir_full(),
                windSpeedKmh,
                conditions,
                data.getAqi(),
                data.getSunrise(),
                data.getSunset(),
                data.getRh(),
                data.getPrecip()
        );
    }
}
