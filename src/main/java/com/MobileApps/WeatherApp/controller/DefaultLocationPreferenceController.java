package com.MobileApps.WeatherApp.controller;

import com.MobileApps.WeatherApp.dto.FavouriteLocationDTO;
import com.MobileApps.WeatherApp.entity.FavouriteLocation;
import com.MobileApps.WeatherApp.middleware.FavouriteLocationMapper;
import com.MobileApps.WeatherApp.service.DefaultLocationPreferenceService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class DefaultLocationPreferenceController {

    private final DefaultLocationPreferenceService service;

    @PostMapping("/default-location/{favId}")
    public FavouriteLocationDTO setDefault(@PathVariable Long favId, Authentication auth) {
        FavouriteLocation defaultPreference = service.setDefaultLocation(auth.getName(), favId);
        return FavouriteLocationMapper.toDTO(defaultPreference);
    }

    @GetMapping("/default-location")
    public FavouriteLocationDTO getDefault(Authentication auth) {
        FavouriteLocation currentDefaultPreference = service.getDefaultLocation(auth.getName());
        return FavouriteLocationMapper.toDTO(currentDefaultPreference);
    }
}
