package com.MobileApps.WeatherApp.controller;

import com.MobileApps.WeatherApp.dto.LocationDTO;
import com.MobileApps.WeatherApp.service.LocationSearchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/locations")
public class LocationSearchController {

    private final LocationSearchService service;

    @GetMapping("/search")
    public List<LocationDTO> search(@RequestParam String query) {
        return service.search(query);
    }
}
