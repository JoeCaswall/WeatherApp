package com.MobileApps.WeatherApp.service;

import com.MobileApps.WeatherApp.dto.LocationDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LocationSearchService {

    private final CityDataLoader loader;

    public List<LocationDTO> search(String query) {
        String q = query.toLowerCase();

        return loader.getCities().stream()
                .filter(c -> c.cityName().toLowerCase().contains(q))
                .map(c -> new LocationDTO(
                        c.cityName(),
                        c.stateCode(),
                        c.countryCode(),
                        c.countryFull(),
                        c.lat(),
                        c.lon()
                ))
                .toList();
    }
}
