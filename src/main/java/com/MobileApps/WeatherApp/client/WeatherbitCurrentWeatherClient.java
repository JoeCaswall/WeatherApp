package com.MobileApps.WeatherApp.client;
import com.MobileApps.WeatherApp.dto.CurrentWeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weatherClient", url = "${weatherbit.api.base-url}")
public interface WeatherbitCurrentWeatherClient {

    @GetMapping("/current/coordinates")
    CurrentWeatherResponse getCurrentWeatherByCoordinates(
            @RequestParam double lat,
            @RequestParam double lon,
            @RequestParam("key") String apiKey
    );

    @GetMapping("/current/cityName")
    CurrentWeatherResponse getCurrentWeatherByCity(
            @RequestParam("city") String cityName,
            @RequestParam("key") String apiKey);
}

