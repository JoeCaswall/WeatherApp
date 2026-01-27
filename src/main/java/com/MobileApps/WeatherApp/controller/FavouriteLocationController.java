package com.MobileApps.WeatherApp.controller;

import com.MobileApps.WeatherApp.dto.FavouriteLocationDTO;
import com.MobileApps.WeatherApp.dto.FavouriteLocationRequestDTO;
import com.MobileApps.WeatherApp.entity.FavouriteLocation;
import com.MobileApps.WeatherApp.middleware.FavouriteLocationMapper;
import com.MobileApps.WeatherApp.service.FavouriteLocationService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favourites")
@AllArgsConstructor
public class FavouriteLocationController {

    private final FavouriteLocationService service;

    @PostMapping
    public FavouriteLocationDTO addFavourite(@RequestBody FavouriteLocationRequestDTO req,
                                             Authentication auth) {
        FavouriteLocation favourite = service.addFavourite(
                auth.getName(),
                req.getCityName(),
                req.getLatitude(),
                req.getLongitude()
        );
        return FavouriteLocationMapper.toDTO(favourite);
    }

    @GetMapping
    public List<FavouriteLocationDTO> getFavourites(Authentication auth) {
        return service.getFavourites(auth.getName())
                .stream()
                .map(FavouriteLocationMapper::toDTO)
                .toList();
    }

    @DeleteMapping("/{id}")
    public void deleteFavourite(@PathVariable Long id, Authentication auth) {
        service.deleteFavourite(id, auth.getName());
    }
}
